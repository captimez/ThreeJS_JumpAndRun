<template>
    <canvas ref="container"></canvas>
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
const cameraFollowThreshold = 0.4;


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

const ground = new THREE.Mesh(
    new THREE.BoxGeometry(10,0.1,1),
    new THREE.MeshBasicMaterial({ color: 0x4339c9})
);
ground.position.y = -0.5;

const sphere = new THREE.Mesh(
    new THREE.SphereGeometry(0.5, 20, 20),
    new THREE.MeshBasicMaterial({ color: 0x585858  })
);

const obstacles = [];
const obstacleSpeed = 0.05; // Speed at which obstacles move toward the player
const spawnInterval = 1000; // Milliseconds between each obstacle spawn

function createObstacle() {
  const obstacle = new THREE.Mesh(
    new THREE.BoxGeometry(0.5, 0.5, 0.5),
    new THREE.MeshBasicMaterial({ color: 0xff0000 }) // Red color for visibility
  );

  // Position obstacle randomly within a certain range, and off-screen to the right
  obstacle.position.set(camera.position.x + 10, Math.random() * 2, 0);
  
  // Add to the scene and to obstacles array
  scene.add(obstacle);
  obstacles.push(obstacle);
}

// Start generating obstacles at regular intervals
setInterval(createObstacle, spawnInterval);

function checkCollision(obj1, obj2) {
  const obj1Box = new THREE.Box3().setFromObject(obj1);
  const obj2Box = new THREE.Box3().setFromObject(obj2);
  return obj1Box.intersectsBox(obj2Box);
}

const lerp = (start, end, amt) => (1-amt) * start + amt * end;
function animate() {
    if (renderer && scene && camera) {
        //Obstacles
        // Move each obstacle towards the player
        obstacles.forEach((obstacle, index) => {
            obstacle.position.x -= obstacleSpeed;

            // Check for collision with the player
            if (checkCollision(obstacle, sphere)) {
            console.log("Collision detected!");
            // Handle collision (e.g., end game, reduce life, etc.)
            }

            // Remove obstacles that are off-screen (e.g., to the left of the player’s camera view)
            if (obstacle.position.x < camera.position.x - 10) {
            scene.remove(obstacle);
            obstacles.splice(index, 1);
            }
        });


        //camera.position.x += 0.005
        // Only move the camera if the player moves past the threshold
        if (sphere.position.x > camera.position.x - cameraFollowThreshold) {
            camera.position.x = lerp(camera.position.x, sphere.position.x - cameraFollowThreshold, 0.1);
        }if(sphere.position.x < camera.position.x + cameraFollowThreshold){
            camera.position.x = lerp(camera.position.x, sphere.position.x + cameraFollowThreshold, 0.1);
        }
        // Bewegung in X- und Z-Richtung basierend auf Tasteneingaben
        if (move.left) sphere.position.x -= speed;
        if (move.right) sphere.position.x += speed;

        // Jump-Mechanik in Y-Richtung
        if (move.jump && !isJumping) {
        velocityY = 0.3; // Anfangsgeschwindigkeit des Sprungs
        isJumping = true;
        }

        // Schwerkraft anwenden
        if (isJumping) {
        velocityY -= gravity; // Schwerkraft verlangsamt den Aufstieg und initiiert den Fall
        sphere.position.y += velocityY;

        // Landen auf y = 0
        if (sphere.position.y <= 0) {
            sphere.position.y = 0;
            isJumping = false; // Sprung zurücksetzen
        }
        }
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




onMounted(() => {
    renderer = new THREE.WebGLRenderer({ canvas: container.value });
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(75, aspectRatio.value, 0.1, 1000);
    scene.back

    scene.add(sphere,ground);
    camera.position.z = 8;
    camera.position.y = 2; // Startposition am Boden

    updateRenderer();

    // Animation starten
    renderer.setAnimationLoop(animate);

    // Event-Listener für Tasten und Maus hinzufügen
    window.addEventListener('keydown', handleKeyDown);
    window.addEventListener('keyup', handleKeyUp);
});

onBeforeUnmount(() => {
    window.removeEventListener('keydown', handleKeyDown);
    window.removeEventListener('keyup', handleKeyUp);
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
  