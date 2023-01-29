import AuthService from "../services/userServices/auth.service";

//  로컬스토리지에서 저장된 user 객체 가져오기
const user = JSON.parse(localStorage.getItem("user"));
const nonUser = JSON.parse(localStorage.getItem("nonUser"));
// user가 있으면
//        initialState( == state) = { status: { loggedIn: true }, user }
// user가 없으면
//        initialState( == state) = { status: { loggedIn: false }, user: null }
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

const initialState2 = nonUser
  ? { status: { loggedIn2: true }, nonUser }
  : { status: { loggedIn2: false }, nonUser: null };

export const auth = {
  namespaced: true,
  state: { initialState, initialState2 },

  actions: {
    login({ commit }, user) {
      return AuthService.login(user)
        .then((user) => {
          commit("loginSuccess", user);
          return Promise.resolve(user);
        })
        .catch((error) => {
          commit("loginFailure");
          return Promise.reject(error);
        });
    },
    login2({ commit }, nonUser) {
      return (
        localStorage.setItem("nonUser", JSON.stringify(nonUser)),
        commit("loginSuccess2", nonUser),
        Promise.resolve(nonUser)
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit("logout");
    },
    // 비회원 로그 아웃 공유 함수
    nonUserLogout({ commit }) {
      AuthService.nonUserLogout();
      commit("nonUserLogout");
    },
    register({ commit }, user) {
      return AuthService.register(user)
        .then((response) => {
          commit("registerSuccess");
          return Promise.resolve(response.data);
        })
        .catch((error) => {
          commit("registerFailure");
          console.log(error)
          return Promise.reject(error);
        });
    },
  },

  mutations: {
    // 회원
    loginSuccess(state, user) {
      state.initialState.status.loggedIn = true;
      state.initialState.user = user;
    },
    // 비회원
    loginSuccess2(state, nonUser) {
      state.initialState2.status.loggedIn2 = true;
      state.initialState2.nonUser = nonUser;
    },
    // 회원
    loginFailure(state) {
      state.initialState.status.loggedIn = false;
      state.initialState.user = null;
    },
    // 비회원
    loginFailure2(state) {
      state.initialState2.status.loggedIn2 = false;
      state.initialState2.nonUser = null;
    },
    // 회원
    logout(state) {
      state.initialState.status.loggedIn = false;
      state.initialState.user = null;
    },
    // 비회원
    nonUserLogout(state) {
      state.initialState2.status.loggedIn2 = false;
      state.initialState2.nonUser = null;
    },

    // 회원가입
    registerSuccess(state) {
      state.initialState.status.loggedIn = false;
    },
    registerFailure(state) {
      state.initialState.status.loggedIn = false;
    },
  },
};
