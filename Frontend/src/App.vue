<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router';
</script>

<!--제일 껍데기 헤더, 푸터 같은거 배치 함-->
<template>
  <header>
    <nav>
      <RouterLink to="/">홈</RouterLink>
      <RouterLink to="/login">로그인</RouterLink>
      <RouterLink to="/register">회원가입</RouterLink>
    </nav>
  </header>
  <!--RouterView는 영화관 스크린이고 입력에 따라 그걸 띄우는 역할임-->
  <main>
    <RouterView />
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
  padding: 2rem;
  border-bottom: 1px solid #ddd;
}

nav {
  width: 100%;
  font-size: 1rem;
  text-align: center;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
  color: var(--color-text);
  text-decoration: none;
}

nav a:first-of-type {
  border: 0;
}

nav a.router-link-exact-active {
  color: hsla(160, 100%, 37%, 1); /* 현재 활성화된 링크의 색상을 변경 */
}

main {
  padding: 2rem;
}
</style>
