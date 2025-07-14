<!-- src/views/RegisterView.vue -->
<script setup lang="ts">
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios'; // 1. axios를 import 합니다.

// 재사용 컴포넌트 import
import LoginInput from '@/components/common/LoginInput.vue';
import LoginButton from '@/components/common/LoginButton.vue';

const router = useRouter();

// 폼 데이터 정의
const form = reactive({
  userId: '',
  password: '',
  confirmPassword: '',
  name: '',
  phone: ''
});

// 회원가입 제출 함수
const handleRegister = async () => { // 2. 비동기 처리를 위해 async를 붙입니다.
  if (form.password !== form.confirmPassword) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }

  try {
    // 3. 백엔드 API에 보낼 데이터 (payload)를 만듭니다.
    //    confirmPassword는 보낼 필요가 없습니다.
    const payload = {
      userId: form.userId,
      password: form.password,
      name: form.name,
      phone: form.phone
    };

    // 4. axios.post를 사용하여 백엔드 회원가입 API를 호출합니다.
    const response = await axios.post('http://localhost:8080/api/users/register', payload);

    // 5. 성공 시 처리
    console.log('회원가입 성공:', response.data);
    alert('회원가입에 성공했습니다! 로그인 페이지로 이동합니다.');
    router.push('/login'); // 로그인 페이지로 이동

  } catch (error) {
    // 6. 실패 시 처리
    console.error('회원가입 실패:', error);
    // 백엔드에서 보낸 에러 메시지를 보여주면 더 좋습니다.
    if (axios.isAxiosError(error) && error.response) {
      alert(`회원가입 실패: ${error.response.data.message}`);
    } else {
      alert('회원가입 중 오류가 발생했습니다.');
    }
  }
};
</script>

<template>
  <div class="register-container">
    <h1>회원가입</h1>
    <form @submit.prevent="handleRegister">
      <LoginInput label="아이디" v-model="form.userId" />
      <LoginInput label="비밀번호" type="password" v-model="form.password" />
      <LoginInput label="비밀번호 확인" type="password" v-model="form.confirmPassword" />
      <LoginInput label="이름" v-model="form.name" />
      <LoginInput label="전화번호" type="tel" v-model="form.phone" />
      <LoginButton text="가입하기" />
    </form>
  </div>
</template>

<style scoped>
/* LoginView와 유사한 스타일을 적용합니다. */
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 2rem;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
}
h1 {
  text-align: center;
  margin-bottom: 1.5rem;
}
</style>
