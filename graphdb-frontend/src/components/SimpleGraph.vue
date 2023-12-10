<template>
  <div id="d3container" />
</template>

<script>
import * as d3 from 'd3'
import axios from 'axios'

export default {
  name: 'SimpleGraph',
  data() {
    return {
      treeData: {
        name: 'Root',
        children: [
          {
            name: 'Child 1',
            children: [{ name: 'Child of child 1' }, { name: 'Child 2 of child 1' }]
          },
          { name: 'Child 2' }
        ]
      },
      fetchedTreeData: '',
      nodes: [],
      links: []
    }
  },
  async mounted() {
    await this.fetchRoot()
    await this.createTree()
  },
  methods: {
    async createTree() {
      this.svg = d3
        .select('div')
        .append('svg')
        .attr('width', 1500)
        .attr('height', 1000)
        .append('g')
        .attr('transform', 'translate(50, 50)')

      const tree = await this.fetchedTreeData
      console.log(tree)
      console.log(this.treeData)
      const root = d3.hierarchy(tree)
      const treeStructure = d3.tree().size([1500, 400])
      this.information = treeStructure(root)

      // const x = 6
      this.connections = this.svg.append('g').selectAll('path').data(this.information.links())
      this.connections
        .enter()
        .append('path')
        .attr('d', (d) => {
          return 'M' + d.source.x + ',' + d.source.y + ' v 55 H' + d.target.x + ' V ' + d.target.y
        })

      const rectangles = this.svg.append('g').selectAll('rect').data(this.information.descendants())
      rectangles
        .enter()
        .append('rect')
        .attr('x', (d) => {
          return d.x - 40
        })
        .attr('y', (d) => {
          return d.y - 40
        })

      this.names = this.svg.append('g').selectAll('text').data(this.information.descendants())
      this.names
        .enter()
        .append('text')
        .text((d) => {
          return d.data.name + '\n' + 'id:' + d.data.id
        })
        .attr('x', (d) => {
          return d.x
        })
        .attr('y', (d) => {
          return d.y - 15
        })
    },
    addNode(node) {
      this.nodes.push(node)
    },
    async fetchRoot() {
      const response = await axios.get('api/person/root')
      console.log(response.data)
      this.fetchedTreeData = response.data
    }
  },
  computed: {}
}
</script>

<style>
rect {
  fill: white;
  stroke: black;
  width: 100px;
  height: 40px;
}
path {
  fill: none;
  stroke: #770865;
}
text {
  color: red;
  text-anchor: middle;
}
</style>
