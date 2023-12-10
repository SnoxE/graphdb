<template>
  <div class="text-2xl text-white">vXD!</div>
  <div ref="d3Container"></div>
</template>

<script>
import * as d3 from 'd3'

export default {
  name: 'VueGraph',
  mounted() {
    this.createTree()
  },
  data() {
    return {
      treeData: [
        {
          name: 'Top Level',
          parent: 'null',
          children: [
            {
              name: 'Level 2: A',
              parent: 'Top Level',
              children: [
                {
                  name: 'Son of A',
                  parent: 'Level 2: A'
                },
                {
                  name: 'Daughter of A',
                  parent: 'Level 2: A'
                },
                {
                  name: 'Another daughter of A',
                  parent: 'Level 2: A'
                }
              ]
            },
            {
              name: 'Level 2: B',
              parent: 'Top Level',
              children: [
                {
                  name: 'Son of A',
                  parent: 'Level 2: A'
                },
                {
                  name: 'Daughter of A',
                  parent: 'Level 2: A'
                },
                {
                  name: 'Another daughter of A',
                  parent: 'Level 2: A'
                }
              ]
            }
          ]
        }
      ],
      tree: null
    }
  },
  methods: {
    createTree() {
      const margin = { top: 20, right: 120, bottom: 20, left: 120 }
      const width = 960 - margin.right - margin.left
      const height = 500 - margin.top - margin.bottom

      this.tree = d3.tree().size([height, width])

      const link = d3
        .linkHorizontal()
        .x(function (d) {
          return d.y
        })
        .y(function (d) {
          return d.x
        })

      const svg = d3
        .select(this.$refs.d3Container)
        .append('svg')
        .attr('width', width + margin.right + margin.left)
        .attr('height', height + margin.top + margin.bottom)
        .append('g')
        .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')')

      const root = this.treeData[0]
      root.x0 = height / 2
      root.y0 = 0

      this.update(root)

      // d3.select(self.frameElement).style("height", "500px"); // This line may not be necessary in Vue
    },
    update(source) {
      const nodes = this.tree(this.root).descendants().reverse()
      const links = this.tree(nodes).links()

      nodes.forEach((d) => {
        d.y = d.depth * 180
      })

      const node = this.svg.selectAll('g.node').data(nodes, (d) => d.id || (d.id = ++this.i))

      const nodeEnter = node
        .enter()
        .append('g')
        .attr('class', 'node')
        .attr('transform', (d) => `translate(${source.y0},${source.x0})`)
        .on('click', (d) => this.click(d))

      nodeEnter
        .append('circle')
        .attr('r', 1e-6)
        .style('fill', (d) => (d._children ? 'lightsteelblue' : '#fff'))

      nodeEnter
        .append('text')
        .attr('x', (d) => (d.children || d._children ? -13 : 13))
        .attr('dy', '.35em')
        .attr('text-anchor', (d) => (d.children || d._children ? 'end' : 'start'))
        .text((d) => d.name)
        .style('fill-opacity', 1e-6)

      const nodeUpdate = node
        .transition()
        .duration(this.duration)
        .attr('transform', (d) => `translate(${d.y},${d.x})`)

      nodeUpdate
        .select('circle')
        .attr('r', 10)
        .style('fill', (d) => (d._children ? 'lightsteelblue' : '#fff'))

      nodeUpdate.select('text').style('fill-opacity', 1)

      const nodeExit = node
        .exit()
        .transition()
        .duration(this.duration)
        .attr('transform', (d) => `translate(${source.y},${source.x})`)
        .remove()

      nodeExit.select('circle').attr('r', 1e-6)

      nodeExit.select('text').style('fill-opacity', 1e-6)

      const link = this.svg.selectAll('path.link').data(links, (d) => d.target.id)

      const linkEnter = link
        .enter()
        .insert('path', 'g')
        .attr('class', 'link')
        .attr('d', (d) => {
          const o = { x: source.x0, y: source.y0 }
          return this.diagonal({ source: o, target: o })
        })

      link.transition().duration(this.duration).attr('d', this.diagonal)

      link
        .exit()
        .transition()
        .duration(this.duration)
        .attr('d', (d) => {
          const o = { x: source.x, y: source.y }
          return this.diagonal({ source: o, target: o })
        })
        .remove()

      nodes.forEach((d) => {
        d.x0 = d.x
        d.y0 = d.y
      })
    }
  }
}
</script>
