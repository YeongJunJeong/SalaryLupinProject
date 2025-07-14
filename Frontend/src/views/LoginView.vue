<script setup lang="ts">
import { reactive } from 'vue';
import { useAuthStore } from '@/stores/auth';   // 백엔드로 쏘는 함수 꺼내오기
import axios from 'axios'; //api 연결

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

const handleLogin = async () => {
  try {
    // 1. [백엔드 연결] 사용자가 입력한 데이터를 백엔드 로그인 API로 보냅니다.
    const response = await axios.post('http://localhost:8080/api/users/login', {
      userId: form.userId,
      password: form.password
    });

    // 2. [성공 처리] 백엔드에서 성공 응답(200 OK)과 함께 사용자 데이터를 받으면,
    console.log('로그인 성공, 서버 응답:', response.data);

    // Pinia 스토어의 login 액션을 호출합니다.
    // 이때, 백엔드로부터 받은 실제 사용자 데이터를 전달합니다.
    authStore.login(response.data);

    alert(`'${response.data.name}'님, 환영합니다!`);

  } catch (error) {
    // 3. [실패 처리] 백엔드에서 에러 응답(4xx, 5xx)이 오면,
    console.error('로그인 실패:', error);
    alert('아이디 또는 비밀번호가 올바르지 않습니다.');
  }
};
</script>

<template>
  <div class="login-container">
    <p>
      Login
    </p>
    <!--<form> 태그에서 submit 이벤트가 발생하면, 페이지 새로고침은 막고(prevent), 우리가 <script>에 정의해 둔 handleLogin 함수를 실행해라.-->
    <form @submit.prevent="handleLogin">
      <!-- 아이디 입력창도 v-model로 form.userId와 연결해야 합니다. -->
      <LoginInput
        label="아이디"
        v-model="form.userId"
      />
      <!-- 비밀번호 입력창도 v-model로 form.password와 연결합니다. -->
      <LoginInput
        label="패스워드"
        type="password"
        v-model="form.password"
      />
      <LoginButton
        text="Login"
      />
    </form>
  </div>
</template>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 2rem;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
}

p {
  text-align: center;
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
}
</style>
