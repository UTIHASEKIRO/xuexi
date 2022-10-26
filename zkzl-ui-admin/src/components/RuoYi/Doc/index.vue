<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'ZkzlDoc',
  data() {
    return {
      url: 'http://www.iocoder.cn/Zkzl/build-debugger-environment/?zkzl'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
