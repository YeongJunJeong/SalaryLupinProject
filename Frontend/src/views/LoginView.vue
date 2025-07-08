<script setup lang="ts">
import { reactive } from 'vue';
import { useAuthStore } from '@/stores/auth';   // 백엔드로 쏘는 함수 꺼내오기
import LoginInput from "@/components/common/LoginInput.vue";
import LoginButton from "@/components/common/LoginButton.vue";

// Pinia 스토어를 가져옵니다.
const authStore = useAuthStore();

interface LoginForm {
  userId: string;
  password: string;
}

const form = reactive<LoginForm>({
  userId: '',
  password: ''
});

const submitLogin = () => {
  if (!form.userId || !form.password) {
    alert('아이디와 비밀번호를 모두 입력해주세요.');
    return;
  }

  // 3. (임시) 로그인 성공 시 넘겨줄 가짜 사용자 데이터를 만듭니다.
  const fakeUser = {
    userId: form.userId,
    name: '홍길동' // 실제로는 백엔드에서 받은 이름
  };

  // 4. 스토어의 login 액션을 호출하고, 가짜 사용자 데이터를 넘겨줍니다.
  authStore.login(fakeUser);

  alert(`'${fakeUser.name}'님, 환영합니다!`);
};


</script>

<!-- <template>과 <style>은 그대로 둡니다. -->
<template> ... </template>

<style scoped>
/* RegisterView.vue의 스타일을 복사해온 뒤, 클래스 이름만 수정 */
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.form-group {
  margin-bottom: 15px;
}
.form-group label {
  display: block;
  margin-bottom: 5px;
}
.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #36a374;
}
</style>
