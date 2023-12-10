<template>
  <div ref="svg"></div>
</template>

<script>
import * as d3 from 'd3'

export default {
  name: 'SimpleGraph',
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
      ]
    }
  },
  mounted() {
    this.createTree()
  },
  methods: {
    createTree() {
      const svg = d3.select(this.$refs.svg)
      const width = 600 // Adjust as needed
      const height = 400 // Adjust as needed

      // Tree layout configuration, now vertical
      const treeLayout = d3.tree().size([height, width - 160])

      // Process the data
      const root = d3.hierarchy(this.treeData[0])
      root.x0 = height / 2
      root.y0 = 0

      // Compute the new tree layout
      const treeData = treeLayout(root)

      // Add links (branches)
      const link = svg
        .selectAll('.link')
        .data(treeData.links())
        .enter()
        .append('path')
        .attr('class', 'link')
        .style('fill', 'none')
        .style('stroke', '#555') // Branch color
        .style('stroke-width', '1.5px')
        .attr(
          'd',
          d3
            .linkVertical()
            .x((d) => d.x)
            .y((d) => d.y)
        )

      // Add each node as a group
      const node = svg
        .selectAll('.node')
        .data(treeData.descendants())
        .enter()
        .append('g')
        .attr('class', 'node')
        .attr('transform', (d) => `translate(${d.x},${d.y})`)

      // Add a circle for each node (leaves/fruits)
      node
        .append('circle')
        .attr('r', 5) // Adjust the size
        .style('fill', '#69b3a2') // Node color

      // Add labels
      node
        .append('text')
        .attr('dy', '.35em')
        .attr('x', (d) => (d.children ? -13 : 13))
        .style('text-anchor', (d) => (d.children ? 'end' : 'start'))
        .text((d) => d.data.name)
    }
  }
}
</script>

<style scoped>
.link {
  stroke: #555; /* Branch color */
  fill: none;
  stroke-width: 1.5px;
}

.node circle {
  fill: #69b3a2; /* Node color */
  stroke: #fff;
  stroke-width: 1.5px;
}

.node text {
  font: 10px sans-serif;
}
</style>
