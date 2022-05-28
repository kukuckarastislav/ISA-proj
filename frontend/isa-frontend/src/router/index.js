import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Registration from '../views/Registration.vue'
import Login from '../views/Login.vue'
import CustomProfile from '../views/CustomerProfile.vue'
import ApprovalRegistrations from '../admin/ApprovalRegistrations'
import CottageOwnerProfile from '../views/CottageOwnerHomePage.vue'
import BoatOwnerProfile from '../views/BoatOwnerHomePage.vue'
import Adventures from '../instructor/Adventures.vue'
import AdventureView from '../instructor/AdventureView.vue'
import CustomerInstructorPage from '../views/CustomerInstructorPage'
import CustomerCottagePage from '../views/CustomerCottagePage'
import CustomerAdventurePage from '../views/CustomerAdventurePage'
import CustomerBoatPage from '../views/CustomerBoatPage'
import NewAdventure from '../instructor/NewAdventure'
import Cottages from '../cottage_owner/Cottages.vue'
import CottageView from '../cottage_owner/CottageView.vue'
import Boats from '../boat_owner/Boats.vue'
import BoatView from '../boat_owner/BoatView.vue'
import InstructorProfile from '../instructor/Profile.vue'
import BoatCreation from '../boat_owner/BoatCreation.vue'
import CustomerReservations from '../views/CustomerReservations.vue'
import AccountDeleteRequest from '../admin/AccountDeleteRequest'
import CottageCreation from '../cottage_owner/CottageCreation.vue'
import RevisionPage from '../admin/RevisionPage.vue'
import AdminProfile from '../admin/Profile.vue'
import ComplaintPage from '../admin/ComplaintPage.vue'
import CustomerSubscriptions from '../views/CustomerSubscriptions.vue'
import LoyaltySettingsPage from '../admin/LoyaltySettingsPage.vue'



const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/registration', name: 'Registration', component: Registration },
  { path: '/login', name: 'Login', component: Login },
  { path: '/customerProfile', name: 'CustomerProfile', component: CustomProfile},
  { path: '/cottageOwnerProfile', name: 'CottageOwnerProfile', component: CottageOwnerProfile},
  { path: '/boatOwnerProfile', name: 'BoatOwnerProfile', component: BoatOwnerProfile},
  { path: '/approval', name: 'ApprovalRegistrations', component: ApprovalRegistrations },
  { path: '/adventures', name: 'Adventures', component: Adventures },
  { path: '/adventure/:adventurename?', name: 'AdventureView', component: AdventureView},
  { path: '/customerInstructorPage/:id?', name: 'CustomerInstructorPage', component: CustomerInstructorPage},
  { path: '/customerCottagePage/:id?', name: 'CustomerCottagePage', component: CustomerCottagePage},
  { path: '/customerAdventurePage/:id?', name: 'CustomerAdventurePage', component: CustomerAdventurePage},
  { path: '/customerBoatPage/:id?', name: 'CustomerBoatPage', component: CustomerBoatPage },
  { path: '/newadventure', name: 'NewAdventure', component: NewAdventure },
  { path: '/cottageOwnerCottages', name: 'Cottages', component: Cottages },
  { path: '/cottageOwnerCottage/:cottagename?', name: 'CottageView', component: CottageView },
  { path: '/boatOwnerBoats', name: 'Boats', component: Boats },
  { path: '/boatOwnerBoat/:boatname?', name: 'BoatView', component: BoatView },
  { path: '/instructorProfile', name: 'InstructorProfile', component: InstructorProfile },
  { path: '/newboat', name: 'BoatCreation', component: BoatCreation },
  { path: '/customerReservations', name: 'CustomerReservations', component: CustomerReservations },
  { path: '/accountdeleteRequest', name: 'AccountDeleteRequest', component: AccountDeleteRequest },
  { path: '/newcottage', name: 'CottageCreation', component: CottageCreation },
  { path: '/revision', name: 'RevisionPage', component: RevisionPage },
  { path: '/adminProfile', name: 'AdminProfile', component: AdminProfile },
  { path: '/adminComplaint', name: 'ComplaintPage', component: ComplaintPage },
  { path: '/customerSubscriptions', name: 'CustomerSubscriptions', component: CustomerSubscriptions },
  { path: '/loyaltySettingsPage', name: 'LoyaltySettingsPage', component: LoyaltySettingsPage}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
