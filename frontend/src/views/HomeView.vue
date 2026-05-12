<script setup>
import img1 from '../assets/2h-media-hH3JuONz7CQ-unsplash.jpg'
import img2 from '../assets/daniele-franchi-yuAOkXNQ9RI-unsplash.jpg'
import img3 from '../assets/alexander-lyashkov-LDUeoEWFPD0-unsplash.jpg'
import img4 from '../assets/aksel-fristrup-w7eaCH6ShR4-unsplash.jpg'
import img5 from '../assets/fox-hyde-XgPf6yNdppI-unsplash.jpg'
import img6 from '../assets/pexels-cottonbro-6218131.jpg'

const images = [img1, img2, img3, img4, img5, img6]
</script>

<template>
 <section class="hero">
   <h2 class="admin">Administratörssida</h2>
   <p>Här kan du administrera medlemmar, hantera spelkatalog och registrera spelsessioner.</p>
 </section>
  <section class="home">
          <div class="collage">
      <img
          v-for="(image, index) in images"
          :key="index"
          :src="image"
          alt="Board game-klubben bild från våra spelsessioner"
      >
    </div>
  </section>
</template>

<style scoped>
.home {
  padding: 2rem 3rem 3rem;

  display: flex;
  justify-content: center;
}

.hero{
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.admin{
  font-size: 1.5rem;
 }
.collage {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;

  justify-content: center;
  align-items: center;

  max-width: 1000px;
}

.collage img {
  width: 300px;
  height: 200px;

  object-fit: cover;

  border-radius: 20px;
  box-shadow: 0 12px 30px rgba(0,0,0,0.35);

  transition: 0.2s;
}


@media (max-width: 900px) {
  .home {
    grid-template-columns: 1fr;
    padding: 1.5rem;
  }

}
</style>