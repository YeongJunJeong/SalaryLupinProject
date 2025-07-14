// 파일 위치: src/main.ts

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import type { Router } from 'vue-router' // 1. Router 타입을 import 합니다.

import App from './App.vue'
import router from './router'

// 2. Pinia 인스턴스를 먼저 생성합니다.
const pinia = createPinia()

// 3. Pinia 플러그인을 정의합니다.
//    이 플러그인은 모든 스토어에 'router'라는 속성을 추가해줍니다.
pinia.use(({ store }) => {
  store.router = router as Router
})

const app = createApp(App)

// 4. 수정한 Pinia 인스턴스와 라우터를 사용합니다.
app.use(pinia)
app.use(router)

app.mount('#app')
