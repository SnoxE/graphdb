<template>
  <div class="bg-dark-mode-gray">
    <div class="mx-auto max-w-screen-xl flex-wrap gap-6 p-4">
      <h1 class="border-b-2 pb-2 text-4xl font-medium text-white">OSOBA</h1>
    </div>
  </div>
  <div class="bg-dark-mode-gray flex w-full flex-col justify-center text-white">
    <div class="mx-auto mt-4 flex flex-row gap-4">
      <form method="post" class="flex flex-col gap-5" @submit.prevent="fetchUserCars">
        <div class="flex flex-col gap-2">
          <label for="person-selector">Wybierz osobę</label>
          <select
            id="person-selector"
            v-model="selectedPerson"
            name="person-selector"
            class="bg-light-gray h-10 rounded pl-2"
          >
            <option v-for="person in userList" :key="person" :value="person">
              {{ person.name }}
            </option>
          </select>
        </div>
        <div class="mx-auto">
          <button type="submit" class="bg-mcl-orange rounded-md p-2 px-10">Pokaż auta</button>
        </div>
      </form>
      <form method="post" class="flex flex-col gap-5 text-white" @submit.prevent="addPerson">
        <div class="flex flex-col gap-2">
          <label for="newPersonName">Podaj imię</label>
          <InputField
            id="newPersonName"
            v-model="newPersonName"
            class="bg-light-gray h-10 rounded pl-2"
            type="text"
            name="newPersonName"
            placeholder="Imię"
            required
          />
        </div>
        <button class="bg-mcl-orange rounded-md p-2 px-10">Dodaj osobę</button>
        <div v-if="newPersonAdded" class="pl-2 text-sm text-white">Dodano osobę</div>
      </form>
      <form
        method="post"
        v-if="selectedPerson != ''"
        class="flex flex-col gap-5 text-white"
        @submit.prevent="addCar"
      >
        <div class="flex flex-col gap-2">
          <label for="newPersonName">Podaj markę</label>
          <InputField
            id="newCarMake"
            v-model="newCarMake"
            class="bg-light-gray h-10 rounded pl-2"
            type="text"
            name="newCarMake"
            placeholder="Renault"
            required
          />
        </div>
        <div class="flex flex-col gap-2">
          <label for="newPersonName">Podaj model</label>
          <InputField
            id="newCarModel"
            v-model="newCarModel"
            class="bg-light-gray h-10 rounded pl-2"
            type="text"
            name="newCarModel"
            placeholder="Clio 4"
            required
          />
        </div>
        <button class="bg-mcl-orange rounded-md p-2 px-10">Dodaj auto</button>
        <div v-if="newCarAdded" class="pl-2 text-sm text-white">Dodano auto</div>
      </form>
    </div>
  </div>

  <div class="bg-dark-mode-gray min-h-screen">
    <div class="mx-auto flex max-w-screen-xl flex-col flex-wrap gap-6 p-4">
      <h1 class="border-b-2 pb-2 text-4xl font-medium text-white">SAMOCHODY</h1>
      <div
        v-for="car in carList"
        :key="car.id"
        class="flex max-w-screen-md rounded-lg border-2 border-transparent text-white hover:border-white"
      >
        <div
          class="bg-light-gray flex flex-1 items-center rounded-l-lg px-8 py-6 text-xl font-medium"
        >
          <span class="text-left"> {{ car.make }}</span>
        </div>
        <div class="bg-dark-gray flex flex-1 items-center px-10 py-6 text-lg">
          <span class="text-left"> {{ car.model }}</span>
        </div>
        <div class="flex items-center px-2">
          <button
            class="border-mcl-orange rounded-md p-2 px-10 text-white hover:border"
            @click="deleteCar(car.id)"
          >
            Usuń
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue'

export default {
  name: 'ReserveForm',
  components: {
    InputField
  },
  data() {
    return {
      userList: [],
      carList: [],
      selectedPerson: '',
      newPersonName: '',
      newPersonAdded: false,
      newCarAdded: false,
      newCarMake: '',
      newCarModel: ''
    }
  },
  async mounted() {
    await this.fetchUsers()
  },
  methods: {
    async fetchUsers() {
      const response = await axios.get('api/person/get-all-users')
      const userList = response.data['content']

      this.userList = []
      userList.forEach((user) => {
        this.userList.push(user)
      })
    },
    async fetchUserCars() {
      const response = await axios.get('api/person/get-user-by-id', {
        params: {
          id: this.selectedPerson.id
        }
      })

      const carList = response.data.carList
      this.carList = []
      carList.forEach((car) => {
        this.carList.push(car)
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
    },
    async addPerson() {
      const response = await axios.post(
        'api/person/add-person',
        {},
        {
          params: {
            name: this.newPersonName
          }
        }
      )

      if (response.status == 200) {
        await this.fetchUsers()
        this.newPersonAdded = true
      }
    },
    async addCar() {
      const response = await axios.post('api/person/' + this.selectedPerson.id + '/add-car', {
        make: this.newCarMake,
        model: this.newCarModel
      })

      if (response.status == 200) {
        await this.fetchUserCars()
        this.newCarAdded = true
      }
    }
  }
}
</script>

<script setup>
import axios from 'axios'
</script>
