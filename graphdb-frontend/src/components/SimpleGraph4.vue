<template>
  <div id="app">
    <tree :data="treeData" node-text="name" layoutType="circular"> </tree>
  </div>
</template>

<script>
import { tree } from 'vued3tree'

export default {
  name: 'SimpleGraph',
  components: {
    tree
  },
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
      }
    }
  }
}
</script>
