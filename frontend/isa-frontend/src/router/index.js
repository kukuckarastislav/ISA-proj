import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Registration from '../views/Registration.vue'
import Login from '../views/Login.vue'
import ApprovalRegistrations from '../admin/ApprovalRegistrations'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/registration', name: 'Registration', component: Registration },
  { path: '/login', name: 'Login', component: Login },
  { path: '/approval', name: 'ApprovalRegistrations', component: ApprovalRegistrations },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
