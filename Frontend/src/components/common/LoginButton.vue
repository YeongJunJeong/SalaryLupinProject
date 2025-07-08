<script setup lang="ts">

defineProps<{
  text: string;
}>()
</script>


<template>
  <!--범용적 사용을 위해 text를 이렇게 놔둠-->
  <button type="submit">{{text}}</button>
</template>

<style scoped>
button {
  width: 100%;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  background-color: #42b983; /* Vue의 상징색 */
  color: white;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.2s;
}
button:hover {
  background-color: #36a374;
}
</style>
