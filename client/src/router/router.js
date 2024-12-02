import { createRouter, createWebHistory } from 'vue-router'
import JumpAndRunGame from '../components/JumpAndRunGame.vue'
import StartView from '../views/StartView.vue'
import HomeView from '../views/HomeView.vue'
import Lobby from '../components/lobby/Lobby.vue'
import LobbyView from '../views/LobbyView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: StartView,
    },
    {
      path:'/lobby',
      name: 'CreateLobby',
      component: HomeView,
    },
    {
      path:'/lobby/:id/game',
      name: 'Analyse',
      component: JumpAndRunGame,
    },{
      path:'/lobby/:id',
      name: 'lobby',
      component: LobbyView,
    }
  ]
})

/* router.beforeEach((to, from, next) => {
  document.title = String(to.name);
  if(to.matched.some(reccord => reccord.meta.requiresAuth)){
    if(!isAuthenticated()){
      next('/login');
    }else{
      next();
    }
  }else{
    next();
  }
  
}); */

export default router