<template>
  <div>
    <h1 class="mx-auto w-full border-b-2 pb-2 text-4xl font-medium text-white">OSOBA</h1>
    <div class="flex flex-col gap-2 text-white">
      <label for="person-selector">Wybierz osobę</label>
      <select
        id="person-selector"
        v-model="selectedPerson"
        name="person-selector"
        class="bg-light-gray h-10 rounded pl-2"
      >
        <option v-for="person in userList" :key="person" :value="person.name">
          {{ person.name }}
        </option>
      </select>
      <button @click="fetchUsers" class="bg-mcl-orange">XD</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FrontPage',
  data() {
    return {
      userList: [],
      selectedPerson: ''
    }
  },
  async mounted() {
    await this.fetchUsers()
  },
  methods: {
    async fetchUsers() {
      const response = await axios.get('api/person/get-all-users')
      const userList = response.data['content']

      userList.forEach((user) => {
        this.userList.push(user)
      })
    },
    async deleteCar(carId) {
      const response = await axios.delete('api/person/delete-car-by-id', {
        params: {
          id: carId
        }
      })

      if (response.status == 200) {
        this.carList.length = 0
        await this.fetchUserCars()
      }
    }
  }
}
</script>
