// Include Viz.js
import { Viz } from "@viz-js/viz";
import { instance } from "@viz-js/viz";

const vizPromise: Promise<Viz> = instance();
export { vizPromise };
(window as any).vizPromise = vizPromise;

// Include echarts
import * as echarts from 'echarts';
(window as any).echarts = echarts;






