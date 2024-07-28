<template>
    <main>
        <Navbar />  <!-- Import and use Navbar component -->
        <div class="my-5">
            <div class="mx-auto" style="max-width: 500px; width: 100%;">
                <h1 class="text-center mb-4">Update Todo</h1>   <!-- Heading for the update page -->
                <form @submit.prevent="updateTodo">
                    <!-- Title Field -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="title" class="form-label" style="font-weight: bold;">Title</label>
                            <input type="text" name="title" id="title" class="form-control" placeholder="Title" required v-model="todo.title"/>
                            <span class="text-danger" v-if="errors.title">{{ errors.title }}</span>
                        </div>
                    </div>

                    <!-- Description Field -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="description" class="form-label" style="font-weight: bold;">Description</label>
                            <input type="text" name="description" id="description" class="form-control" placeholder="Description" required v-model="todo.description"/>
                            <span class="text-danger" v-if="errors.description">{{ errors.description }}</span>
                        </div>
                    </div>

                     <!-- Date Field -->
                     <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="date" class="form-label" style="font-weight: bold;">Date</label>
                            <input type="date" name="date" id="date" class="form-control" required v-model="todo.date"/>
                            <span class="text-danger" v-if="errors.date">{{ errors.date }}</span>
                        </div>
                    </div>

                    <!-- Priority Field -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="priority" class="form-label" style="font-weight: bold;">Priority</label>
                            <select name="priority" id="priority" class="form-control" v-model="todo.priority">
                                <option value="Low">Low</option>
                                <option value="Medium">Medium</option>
                                <option value="High">High</option>
                            </select>
                            <span class="text-danger" v-if="errors.priority">{{ errors.priority }}</span>
                        </div>
                    </div>
                    
                    <!-- Completed Checkbox -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="completed" class="form-label" style="font-weight: bold;">Completed</label>
                            <div class="checkbox-container">
                                <input type="checkbox" class="large-checkbox" id="completed" v-model="todo.completed"/>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Submit Button -->
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary w-100">Submit</button>
                    </div>
                    
                    <!-- Success/Error Message -->
                    <div v-if="successMessage" class="alert alert-success">
                        {{ successMessage }}
                    </div>
                    <div v-if="errorMessage" class="alert alert-danger">
                        {{ errorMessage }}
                    </div>
                </form>
            </div>
        </div>
    </main>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Navbar from '../components/Navbar.vue';
import '../assets/styles.css'; // Import the new CSS file

const route = useRoute();
const router = useRouter();

// Reactive variable for the todo object with Record type
const todo = ref<Record<string, any>>({
    id: '',
    title: '',
    description: '',
    date: '',
    priority: '',
    completed: false 
});

const errors = ref<Record<string, string>>({});
const successMessage = ref<string>('');
const errorMessage = ref<string>('');

// Fetch todo details from the server
const getTodo = async () => {
    try {
        const response = await fetch(`http://localhost:8080/todo/${route.params.id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        todo.value = data;  // Update todo with fetched data
        console.log(todo.value);  // Log todo data for debugging
    } catch (error) {
        console.error('Error fetching todo:', error);   // Log errors
        errorMessage.value = 'Failed to fetch todo details.';
    }
};

// Validate form inputs
const validateForm = () => {
    errors.value = {};  // Reset errors
    let isValid = true;

    // Title validation
    if (!todo.value.title.trim()) {
        errors.value.title = 'Title is required.';
        isValid = false;
    } else if (todo.value.title.length < 2) {
        errors.value.title = 'Title must have more than 1 character.';
        isValid = false;
    }

    // Description validation
    if (!todo.value.description.trim()) {
        errors.value.description = 'Description is required.';
        isValid = false;
    } else if (todo.value.description.length < 3) {
        errors.value.description = 'Description must have more than 2 characters.';
        isValid = false;
    }

    // Date validation
    if (!todo.value.date) {
        errors.value.date = 'Date is required.';
        isValid = false;
    } else if (new Date(todo.value.date) <= new Date()) {
        errors.value.date = 'Date cannot be in the past.';
        isValid = false;
    }

    // Priority validation
    if (!todo.value.priority) {
        errors.value.priority = 'Priority is required.';
        isValid = false;
    }

    return isValid;
};

// Update the todo on the server
const updateTodo = async () => {
    if (validateForm()) {
        try {
            const response = await fetch('http://localhost:8080/todo', {
                method: 'PUT',  // HTTP method for updating existing todo
                headers: {
                    'Content-Type': 'application/json'  // Specify JSON content type
                },
                body: JSON.stringify(todo.value)    // Convert todo object to JSON
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);  // Log success message
            successMessage.value = 'Todo updated successfully!';
            errorMessage.value = ''; // Clear any previous errors
            router.push('/');   // Redirect to home page after successful update
        } catch (error) {
            errorMessage.value = 'Error updating todo: ' + error.message;
            successMessage.value = ''; // Clear any previous success messages
        }
    }
};

// Fetch todo details when component is mounted
onMounted(() => {
    getTodo();
});
</script>
