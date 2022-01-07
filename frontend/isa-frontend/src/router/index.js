import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Registration from '../views/Registration.vue'
import Login from '../views/Login.vue'
import CustomProfile from '../views/CustomerProfile.vue'
import ApprovalRegistrations from '../admin/ApprovalRegistrations'
import CottageOwnerHomePage from '../views/CottageOwnerHomePage.vue'
import Adventures from '../instructor/Adventures.vue'
import AdventureView from '../instructor/AdventureView.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/registration', name: 'Registration', component: Registration },
  { path: '/login', name: 'Login', component: Login },
  {path: '/customerProfile', name: 'CustomerProfile', component: CustomProfile},
  {path: '/cottageOwnerProfile', name: 'CottageOwnerHomePage', component: CottageOwnerHomePage},
  { path: '/approval', name: 'ApprovalRegistrations', component: ApprovalRegistrations },
  { path: '/adventures', name: 'Adventures', component: Adventures },
  { path: '/adventure/:adventurename?', name: 'AdventureView', component: AdventureView},
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
