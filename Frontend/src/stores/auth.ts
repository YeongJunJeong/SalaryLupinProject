// 파일 위치: src/stores/auth.ts

import { defineStore } from 'pinia'
import type { Router } from 'vue-router' // Router 타입을 import

// 스토어의 router 속성을 사용하기 위한 타입 확장 (TypeScript 트릭)
declare module 'pinia' {
  export interface PiniaCustomProperties {
    router: Router;
  }
}

// 사용자 정보 타입 정의
interface User {
  userId: string;
  name: string;
}

// 옵션 스토어 방식으로 변경합니다.
export const useAuthStore = defineStore('auth', {
  // state: 스토어의 상태(데이터)를 정의합니다. (ref 대신 사용)
  state: () => ({
    isLoggedIn: false,
    user: null as User | null
  }),

  // getters: 계산된 속성을 정의합니다. (computed 대신 사용)
  getters: {
    userName: (state) => state.user?.name
  },

  // actions: 상태를 변경하는 메소드를 정의합니다.
  actions: {
    login(loginUser: User) {
      this.user = loginUser
      this.isLoggedIn = true

      // 이제 this.router를 통해 안전하게 라우터에 접근할 수 있습니다.
      this.router.push('/')
    },

    logout() {
      this.user = null
      this.isLoggedIn = false
      this.router.push('/')
    }
  }
})
