<template>
    <main class="full-height">
        <!-- Navbar component for navigation -->
        <Navbar />
        
        <!-- Container for the main content -->
        <div class="full-width-container">
            
            <!-- Header and add button -->
            <h1 class="text-center mb-4">View Todos</h1>
            <a href="/add" class="btn btn-primary mb-3">Add Todo</a>
            
            <!-- Scrollable container for the table -->
            <div class="table-container" v-if="todos.length > 0">
                <!-- Table to display the list of todos -->
                <table class="table">
                    <thead>
                        <tr>
                            <!-- Table headers -->
                            <th scope="col" style="font-weight: bold;">Title</th>
                            <th scope="col" style="font-weight: bold;">Description</th>
                            <th scope="col" style="font-weight: bold;">Date</th>
                            <th scope="col" style="font-weight: bold;">Priority</th>
                            <th scope="col" style="font-weight: bold;">Completed</th>
                            <th scope="col" style="font-weight: bold;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through each todo item and display in table rows -->
                        <tr v-for="todo in todos" :key="todo.id">
                            <td>{{ todo.title }}</td>
                            <td>{{ todo.description }}</td>
                            <td>{{ todo.date }}</td>
                            <td>
                                <div :class="['priority-box', priorityClass(todo.priority)]">
                                    {{ todo.priority }}
                                </div>
                            </td>
                            <td>
                                <!-- Checkbox to mark the todo as completed or not -->
                                <input type="checkbox" class="large-checkbox" :checked="todo.completed" @change="toggleCompletion(todo)"/>
                            </td>
                            <td>
                                <!-- Buttons for editing and deleting a todo -->
                                <a class="btn btn-primary" :href="`/edit/${todo.id}`">Edit</a>
                                <button class="btn btn-danger mx-2" @click="deleteTodo(todo.id)">Delete</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import '../assets/styles.css'; // Import the new CSS file

export default {
    name: 'ViewTodos',
    components: {
        Navbar
    },
    data() {
        return {
            todos: []  // Array to store the list of todos
        }
    },
    methods: {
        // Method to fetch todos from the server
        getTodos() {
            fetch('http://localhost:8080/todo')
                .then(res => {
                    if (!res.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return res.json();  // Parse the response as JSON
                })
                .then(data => {
                    this.todos = data; // Update the todos data
                    console.log("Fetched todos:", data);
                })
                .catch(error => console.error('Error fetching todos:', error));
        },
        
        // Method to delete a todo by its ID
        deleteTodo(id) {
            console.log(`Attempting to delete todo with id: ${id}`);
            fetch(`http://localhost:8080/todo/${id}`, {
                method: 'DELETE'  // HTTP method for deletion
            })
            .then(res => {
                if (!res.ok) {
                    throw new Error(`HTTP error! status: ${res.status}`);
                }
                return res.text(); // Handle response as plain text
            })
            .then(data => {
                console.log(`Todo with id ${id} deleted successfully. Response:`, data);
                this.getTodos(); // Refresh the list after deletion
            })
            .catch(error => console.error('Error deleting todo:', error));
        },
        
        // Method to toggle the completion status of a todo
        toggleCompletion(todo) {
            // Create a new object with the updated completed status
            const updatedTodo = { ...todo, completed: !todo.completed };

            fetch(`http://localhost:8080/todo`, {
                method: 'PUT', // HTTP method for update
                headers: {
                    'Content-Type': 'application/json'  // Content type of the request
                },
                body: JSON.stringify(updatedTodo)   // Send the updated todo as JSON
            })
            .then(res => {
                if (!res.ok) {
                    throw new Error('Network response was not ok');
                }
                return res.json();  // Parse the response as JSON
            })
            .then(data => {
                console.log('Todo updated:', data);
                this.getTodos(); // Refresh the list after update
            })
            .catch(error => console.error('Error updating todo:', error));
        },

         // Method to return the class based on priority
         priorityClass(priority) {
            switch (priority) {
                case 'High':
                    return 'priority-high';
                case 'Medium':
                    return 'priority-medium';
                case 'Low':
                    return 'priority-low';
                default:
                    return '';
            }
        }

    },
    mounted() {
        this.getTodos(); // Fetch todos when component is mounted
    }
}
</script>
