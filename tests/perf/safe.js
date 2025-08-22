import http from 'k6/http';
import { check, sleep } from 'k6';

// Apunta a tu app local
const BASE = __ENV.BASE_URL || 'http://localhost:8080';
const PATH = __ENV.BASE_PATH || '/conversor/'; // usa "/" si no tienes context-path

export const options = {
  // Carga controlada por RPS (no por VUs): mucho más predecible para tu CPU
  scenarios: {
    smoke: {
      executor: 'constant-arrival-rate',
      rate: __ENV.RATE ? parseInt(__ENV.RATE) : 5,   // 5 req/s
      timeUnit: '1s',
      duration: __ENV.DUR || '60s',
      preAllocatedVUs: __ENV.VUS ? parseInt(__ENV.VUS) : 10, // reserva pocos VUs
      maxVUs: __ENV.MAXVUS ? parseInt(__ENV.MAXVUS) : 10,
      exec: 'page',
      gracefulStop: '10s',
    },
  },

  // Evita cargar de más la red/memoria
  discardResponseBodies: true,

  // Corta temprano si algo va mal (protege tu equipo)
  thresholds: {
    http_req_failed: [{ threshold: 'rate<0.05', abortOnFail: true }],   // <5% errores
    http_req_duration: [{ threshold: 'p(95)<1200', abortOnFail: true }],// p95 < 1.2s
  },
};

export function page() {
  const r = http.get(`${BASE}${PATH}conversor`); // p.ej. http://localhost:8080/conversor/
  check(r, {
    'status OK/redirect/auth':
      x => [200,201,202,204,301,302,303,307,308,401].includes(x.status),
  });
  sleep(1); // respira: baja CPU
}
