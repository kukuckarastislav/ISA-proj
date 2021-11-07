const homePage = { template: '<homePage></homePage>' }


const router = new VueRouter({
	mode: 'hash',
	routes: [
		{ path: '/', component: homePage }
	]
});



var app = new Vue({
	router,
	el: '#initialDiv',
	data: {
		
	},
	mounted() {

	},
	methods: {

	}
});



