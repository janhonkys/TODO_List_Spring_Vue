// Import the main CSS file and Bootstrap CSS
import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.min.css'

// Import Vue and createApp function
import { createApp } from 'vue'
// Import the root App component
import App from './App.vue'
// Import the router configuration
import router from './router'

// Create a Vue application instance with the App component
const app = createApp(App)

// Use the router in the Vue application
app.use(router)

// Mount the Vue application to the DOM element with id 'app'
app.mount('#app')
