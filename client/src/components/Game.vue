<template>
    <canvas ref="container"></canvas>
</template>
  
<script setup>
import { ref, onMounted } from 'vue';
import * as THREE from 'three';

const container = ref(null);

const sphere = new THREE.Mesh(new THREE.SphereGeometry(1,20,20),new THREE.MeshBasicMaterial({ color: 0x009090 }))
const ambientLight = new THREE.AmbientLight(0x111111, 1000)

onMounted(() => {
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1 ,1000);

scene.add(sphere)

camera.position.z = 5;

const renderer = new THREE.WebGLRenderer({
    canvas: container.value
});
renderer.setSize(window.innerWidth,window.innerHeight);
renderer.render(scene,camera)
});
</script>

<style scoped>
canvas{
    width: 100%;
    height: 100%;
}
</style>
  