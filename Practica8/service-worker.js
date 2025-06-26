const CACHE_NAME = 'notas-rapidas-v1';
const ASSETS_TO_CACHE = [
  '/',
  '/index.html',
  '/styles.css',
  '/app.js',
  '/manifest.json'
];

self.addEventListener('install', event => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => {
        return cache.addAll(ASSETS_TO_CACHE.filter(url => {
          return !url.includes('icon-');
        }));
      })
      .catch(err => {
        console.log('Cache addAll error:', err);
      })
  );
});