import { Pact } from '@pact-foundation/pact';

const path = require('path');

export const CONSUMER = 'ED-SHOWCASE-UI';
export const PROVIDER = 'ED-SHOWCASE-SERVICE'; // There could be other providers but for the moment this is the only one.

// We pass provider as a parameter as there could be different ones, where consumer is always this application here.
const providerBuilder = (provider) =>
  new Pact({
    cors: true,
    log: path.resolve(process.cwd(), 'logs', 'pact.log'),
    loglevel: 'debug',
    dir: path.resolve(process.cwd(), 'pacts'),
    spec: 2,
    pactfileWriteMode: 'update',
    consumer: CONSUMER,
    provider,
    host: 'localhost',
  });

export default providerBuilder;
