
// Include PivotTable.js
const $ = require('jquery');
(window as any).$ = $;
(window as any).JQuery = $;
import 'jquery-ui/ui/widgets/sortable';
import 'pivottable';
import './js/export_renderers.js';
import 'file-saver';
import { saveRowColumnConfig } from './pivot_aht_functions';
(window as any).saveRowColumnConfig = saveRowColumnConfig;


