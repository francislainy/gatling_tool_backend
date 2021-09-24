import { Matchers } from '@pact-foundation/pact';
import { getSchool } from '../../../apis/ids';
import providerBuilder, { PROVIDER } from '../../helpers/pactSetup';

const { somethingLike, uuid } = Matchers;

const GET_EXPECTED_BODY = {
  name: somethingLike('InteractEd Test Eval School 1-91002661'),
  type: 'SCHOOL',
  sifRefId: uuid('54d2a3eb-0010-4988-9d41-ec68ea869399'),
  pid: somethingLike('91002661'),
  schoolId: uuid('54d2a3eb-0010-4988-9d41-ec68ea869399'),
  parentOrg: {
    orgId: uuid('fabe7fcc-6c79-4540-94ee-3f22072ddb7a'),
    name: somethingLike('InteractEd Test Eval District 1-91002660'),
    type: somethingLike('DISTRICT'),
    sifRefId: uuid('fabe7fcc-6c79-4540-94ee-3f22072ddb7a'),
    pid: somethingLike('91002660'),
  },
};

const provider = providerBuilder(PROVIDER);

beforeAll(() => {
  return provider.setup().then((opts) => {
    process.env.API_PORT = opts.port;
  });
});

afterAll(() => provider.finalize());

afterEach(() => provider.verify());

describe('GET School', () => {
  beforeEach((done) => {
    const interaction = {
      state: 'i have a school',
      uponReceiving: 'a request for a school',
      withRequest: {
        method: 'GET',
        path: '/ids/v1/schools/0d2bf746-ae98-4bb4-a807-8c2db6d2852d',
        headers: {
          Accept: '*/*',
        },
      },
      willRespondWith: {
        status: 200,
        headers: {
          'Content-Type': 'application/json',
        },
        body: GET_EXPECTED_BODY,
      },
    };
    provider.addInteraction(interaction).then(() => done());
  });

  test('generate contract', async () => {
    const response = await getSchool(
      { schoolId: '0d2bf746-ae98-4bb4-a807-8c2db6d2852d' },
      'mockToken',
      {
        baseUrl: `${provider.mockService.baseUrl}/ids/v1`,
      },
    );
    expect(response.status).toEqual(200);
  });
});
