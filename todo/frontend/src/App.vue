<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
/* Ensure html and body take full height */
html, body {
  height: 100%; /* Ensure html and body take full height */
  margin: 0; /* Remove default margin */
}

#app {
  display: flex;
  flex-direction: column;
  height: 100vh; /* Full viewport height */
}
</style>
