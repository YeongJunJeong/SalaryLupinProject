<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router';
</script>
<!--제일 껍데기 헤더, 푸터 같은거 배치 함-->
<!--헤더와 푸터는 공동 레이아웃-->
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
