console.log("This ist Metachart 0.0.8-SNAPSHOT ... using Highcharts version 11.4.3");
import Highcharts from 'highcharts/es-modules/masters/highcharts.src.js';
// Load the exporting module

import 'highcharts/modules/data';
import 'highcharts/modules/exporting';
import 'highcharts/modules/export-data';
import 'highcharts/modules/broken-axis';
import 'highcharts/modules/map';
import 'highcharts/modules/heatmap';
(window as any).Highcharts = Highcharts;

import './heat_bar';

// Include connectChartTable
import { selectElementInChart, selectRowInTable, selectGraphPoint } from './connectChartTable';
(window as any).selectElementInChart = selectElementInChart;
(window as any).selectRowInTable = selectRowInTable;
(window as any).selectGraphPoint = selectGraphPoint;
