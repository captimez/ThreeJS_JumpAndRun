<template>
    <div ref="gameContainer"></div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import * as THREE from 'three';
  
  const gameContainer = ref(null);
  
  onMounted(() => {
    const container = gameContainer.value;
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(75, container.clientWidth / container.clientHeight, -5, 1000);
    const renderer = new THREE.WebGLRenderer();
  
    renderer.setSize(container.clientWidth, container.clientHeight);
    container.appendChild(renderer.domElement);
  
    // Add lighting
    const ambientLight = new THREE.AmbientLight(0x404040); // soft white light
    scene.add(ambientLight);
  
    const directionalLight = new THREE.DirectionalLight(0xffffff, 1); // bright white light
    directionalLight.position.set(5, 5, 5).normalize();
    scene.add(directionalLight);
  
    // Create a cube with a material that interacts with light
    const geometry = new THREE.BoxGeometry();
    const material = new THREE.MeshStandardMaterial({ color: 0x00ff00 });
    const cube = new THREE.Mesh(geometry, material);
    scene.add(cube);
  
    camera.position.z = 5;
  
    function animate() {
      requestAnimationFrame(animate);
      cube.rotation.x += 0.01;
      cube.rotation.y += 0.01;
      renderer.render(scene, camera);
    }
  
    animate();
  });
  </script>
  
  <style scoped>
  div {
    width: 100%;
    height: 100vh;
  }
  </style>
  