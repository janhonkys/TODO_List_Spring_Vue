// Import Vue Router functions and components
import { createRouter, createWebHistory } from 'vue-router';
import ViewTodos from '../views/ViewTodos.vue';

// Create and configure the router
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL), // Use HTML5 History API
  routes: [
    {
      path: '/', // Root path
      name: 'home', // Route name
      component: ViewTodos // Component for this route
    },
    {
      path: '/add', // Path for adding a new todo
      name: 'add', // Route name
      component: () => import('../views/AddTodo.vue') // Lazy-load component
    },
    {
      path: '/edit/:id', // Path for editing a specific todo
      name: 'edit', // Route name
      component: () => import('../views/UpdateTodo.vue') // Lazy-load component
    }
  ]
});

// Export the router instance
export default router;
