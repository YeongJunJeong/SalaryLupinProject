// 함수는 여기 정의 됩니다

import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { useRouter } from "vue-router";
import router from "@/router";

//변수 타입 정의 여기서 함
interface User {
  userid: string;
  name: string;
}

export const useAuthStore = defineStore("auth", () => {
  //상태
  //ref 반응형 상태 로그인 여부를 저장
  //React useState와 유사함
  const isLoggedIn = ref(false)

  //user가 로그인 했는지 null 인지 저장
  const user = ref<User | null>(null)

  //게터
  const userName = computed(() => user.value?.name)

  //액션
  /**
   * 로그인 액션
   * @param loginUser 로그인한 사용자 정보 객체
   */
  function login(loginUser: User){
    const router = useRouter();
    user.value = loginUser
    isLoggedIn.value = true
    router.push('/')
}

  function logout() {
    const router = useRouter();
    user.value = null
    isLoggedIn.value = false
    router.push('/')
}

return {
  isLoggedIn,
  user,
  userName,
  login,
  logout
}
})
