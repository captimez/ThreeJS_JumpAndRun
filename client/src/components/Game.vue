<template>
    <canvas ref="container" @click="lockPointer"></canvas>
</template>
  
<script setup>
import { ref, onMounted, computed, watch, onBeforeUnmount } from 'vue';
import * as THREE from 'three';
import { useWindowSize } from '@vueuse/core';

const container = ref(null);
const { width, height } = useWindowSize();
const aspectRatio = computed(() => width.value / height.value);

let renderer, scene, camera;
const move = { forward: false, backward: false, left: false, right: false, jump: false };
const speed = 0.1;
const gravity = 0.01;
let velocityY = 0;
let isJumping = false;

let yaw = 0;
let pitch = 0;
const mouseSensitivity = 0.002;

function updateRenderer() {
    if (renderer) {
        renderer.setSize(width.value, height.value);
        renderer.setPixelRatio(window.devicePixelRatio);
    }
}

function updateCamera() {
    if (camera) {
        camera.aspect = aspectRatio.value;
        camera.updateProjectionMatrix();
    }
}

watch(aspectRatio, () => {
    updateRenderer();
    updateCamera();
});

const sphere = new THREE.Mesh(
    new THREE.SphereGeometry(1, 20, 20),
    new THREE.MeshBasicMaterial({ color: 0x009090 })
);

function animate() {
    if (renderer && scene && camera) {
        // Bewegung in X- und Z-Richtung basierend auf Tasteneingaben
        if (move.forward) camera.position.z -= speed;
        if (move.backward) camera.position.z += speed;
        if (move.left) camera.position.x -= speed;
        if (move.right) camera.position.x += speed;

        // Jump-Mechanik in Y-Richtung
        if (move.jump && !isJumping) {
        velocityY = 0.2; // Anfangsgeschwindigkeit des Sprungs
        isJumping = true;
        }

        // Schwerkraft anwenden
        if (isJumping) {
        velocityY -= gravity; // Schwerkraft verlangsamt den Aufstieg und initiiert den Fall
        camera.position.y += velocityY;

        // Landen auf y = 0
        if (camera.position.y <= 0) {
            camera.position.y = 0;
            isJumping = false; // Sprung zurücksetzen
        }
        }

        // Yaw und Pitch anwenden, um Kamera-Rotation zu steuern
        camera.rotation.y = yaw;
        camera.rotation.x = pitch;

        renderer.render(scene, camera);
    }
}

function handleKeyDown(event) {
    switch (event.key) {
        case 'w':
        move.forward = true;
        break;
        case 's':
        move.backward = true;
        break;
        case 'a':
        move.left = true;
        break;
        case 'd':
        move.right = true;
        break;
        case ' ': // Leertaste
        move.jump = true;
        break;
    }
}

function handleKeyUp(event) {
    switch (event.key) {
        case 'w':
        move.forward = false;
        break;
        case 's':
        move.backward = false;
        break;
        case 'a':
        move.left = false;
        break;
        case 'd':
        move.right = false;
        break;
        case ' ':
        move.jump = false;
        break;
    }
}

function lockPointer() {
    container.value.requestPointerLock();
}

function handleMouseMove(event) {
    if (document.pointerLockElement === container.value) {
        yaw -= event.movementX * mouseSensitivity;
        pitch -= event.movementY * mouseSensitivity;
        pitch = Math.max(-Math.PI / 2, Math.min(Math.PI / 2, pitch)); // Limit für Pitch, um Überkopf-Ansicht zu verhindern
    }
}

onMounted(() => {
    renderer = new THREE.WebGLRenderer({ canvas: container.value });
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(75, aspectRatio.value, 0.1, 1000);

    scene.add(sphere);
    camera.position.z = 5;
    camera.position.y = 0; // Startposition am Boden

    updateRenderer();

    // Animation starten
    renderer.setAnimationLoop(animate);

    // Event-Listener für Tasten und Maus hinzufügen
    window.addEventListener('keydown', handleKeyDown);
    window.addEventListener('keyup', handleKeyUp);
    window.addEventListener('mousemove', handleMouseMove);
});

onBeforeUnmount(() => {
    window.removeEventListener('keydown', handleKeyDown);
    window.removeEventListener('keyup', handleKeyUp);
    window.removeEventListener('mousemove', handleMouseMove);
});
</script>

<style scoped>
canvas {
    display: block;
    width: 100vw;
    height: 100vh;
    margin: 0;
    padding: 0;
    overflow: hidden;
}
body, html {
    margin: 0;
    padding: 0;
    overflow: hidden;
}
</style>
  