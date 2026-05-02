<template>
  <nav>
    <button @click="$emit('change-page', 'members')">Hantera medlemskap</button>
    <button @click="$emit('change-page', 'rounds')">Registrera spelomgång</button>
    <button @click="$emit('change-page', 'library')">Hantera spelbibliotek</button>
    <button @click="$emit('change-page', 'statistics')">Visa statistik</button>
  </nav>
</template>