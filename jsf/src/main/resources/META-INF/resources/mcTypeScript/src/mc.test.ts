import { jest } from '@jest/globals';
import { describe, expect, test, beforeEach } from '@jest/globals';
import { JSDOM } from 'jsdom';
const dom = new JSDOM();
(global as any).window = dom.window;

import { vizPromise } from './mc';

test('test pivot utilities', () => {
    console.log("result");
});
/*
test('test instance', () => {
    const graphvizVersion = vizPromise.then((viz) => {
        return viz.graphvizVersion;
    });
});
*/
