<script setup lang="ts">

//defineModel로 v-model 정의 함
const model  = defineModel<string>();

defineProps<{
  label: string; // 입력창에 나오는 라벨
  type?: string; // 범용적인 인풋일때 이게 좋음
}>()
</script>

<template>
  <div class="form-container">
    <!--범용적 사용을 위해 lable을 이렇게 놔둠-->
    <label class="login-label">{{label}}</label>
    <input :type="type || 'text'" v-model="model" required />
  </div>
</template>

<style scoped>
.form-group {
  margin-bottom: 1rem;
}
.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
}
.form-group input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box; /* 패딩과 테두리가 너비에 포함되도록 설정 */
}
</style>
