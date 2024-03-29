// ========================================
// datatable jQuery plug-in
// ========================================
//
// datatable.js
// Version: 0.2.6
//
// Copyright 2015-2017 Toni Heittola (toni.heittola@gmail.com)
// Released under the MIT license
//
// ========================================
jQuery(document).ready(function () {
    jQuery('table.datatable').each(function () {
        jQuery(this).datatable();
    });
});

(function ($, window, document, undefined) {
    "use strict";
    var pluginName = 'datatable';
    var pluginVersion = "0.2.5";
    window.datatable_uniqId = 0;

    // Defaults
    var defaults = {
        debug: false
    };

    // Define the commands that can be used in this plugin
    var commands = {
        getRank: getRank,
        updateChart: updateChart,
    };

    var Plugin = function (element, options, name) {
        this.init(element, options, name);
    };
    Plugin.prototype = {
        defaults: {
            debug: false,
            rank_mode: 'normal', // 'normal', 'grouped-muted', 'grouped-empty'
            tag_mode: 'column', // global, column
            show_chart: false,
            chart_modes: 'bar',
            chart_default_mode: 'bar',
            list_separator: ',',
            css: {
                active: 'active'
            },
            animation: {
                duration: 100,
                show_speed: 200,
                hide_speed: 200
            },
            table: {
                id_field: 'id',
                row_highlighting: false,

                // bootstrap-table.js specific defaults
                page_list: '[5,10,20,50,all]',
                pagination: true,
                show_header: true,
                show_columns: true,
                show_pagination_switch: true,

                striped: true
            },
            chart: {
                position: 'top'
            },
            bar: {
                height: 240,
                show_bar_chart_xaxis: true,
                hline: {
                    enabled: false,
                    strokeStyle: 'rgba(112, 187, 225 ,0.5)',
                    lineWidth: 2
                },
                colors: {
                    valid: ['active', 'success', 'info', 'warning', 'danger'],
                    default: {
                        border: {
                            normal: 'rgba(233,144,2, 0.5)',
                            hover: 'rgba(233,144,2, 1)'
                        },
                        bg: {
                            normal: 'rgba(233,144,2,0.2)',
                            hover: 'rgba(233,144,2,0.4)'
                        }
                    },
                    active: {
                        border: {
                            normal: 'rgba(160, 160, 160, 0.5)',
                            hover: 'rgba(160, 160, 160 1)'
                        },
                        bg: {
                            normal: 'rgba(160, 160, 160, 0.4)',
                            hover: 'rgba(160, 160, 160 0.8)'
                        }
                    },
                    success: {
                        border: {
                            normal: 'rgba(92, 184, 92, 0.5)',
                            hover: 'rgba(92, 184, 92, 1)'
                        },
                        bg: {
                            normal: 'rgba(92, 184, 92, 0.4)',
                            hover: 'rgba(92, 184, 92, 0.8)'
                        }
                    },
                    info: {
                        border: {
                            normal: 'rgba(112, 187, 225, 0.5)',
                            hover: 'rgba(112, 187, 225, 1)'
                        },
                        bg: {
                            normal: 'rgba(112, 187, 225, 0.4)',
                            hover: 'rgba(112, 187, 225, 0.8)'
                        }
                    },
                    warning: {
                        border: {
                            normal: 'rgba(240, 173, 78, 0.5)',
                            hover: 'rgba(240, 173, 78, 1)'
                        },
                        bg: {
                            normal: 'rgba(240, 173, 78, 0.4)',
                            hover: 'rgba(240, 173, 78, 0.8)'
                        }
                    },
                    danger: {
                        border: {
                            normal: 'rgba(217, 83, 79, 0.5)',
                            hover: 'rgba(217, 83, 79, 1)'
                        },
                        bg: {
                            normal: 'rgba(217, 83, 79, 0.4)',
                            hover: 'rgba(217, 83, 79, 0.8)'
                        }
                    },
                    background: 'rgba(251, 85, 85, 0.8)'
                },
                tooltips: {
                    titleFontSize: 18,
                    bodyFontSize: 14
                }
            },
            scatter: {
                height: 200,
                colors: {
                    valid: ['active', 'success', 'info', 'warning', 'danger'],
                    default: {
                        border: {
                            normal: 'rgba(233,144,2, 0.5)',
                            hover: 'rgba(233,144,2, 1)'
                        },
                        bg: {
                            normal: 'rgba(233,144,2,0.5)',
                            hover: 'rgba(233,144,2,0.8)'
                        }
                    },
                    active: {
                        border: {
                            normal: 'rgba(160, 160, 160, 0.5)',
                            hover: 'rgba(160, 160, 160 1)'
                        },
                        bg: {
                            normal: 'rgba(160, 160, 160, 0.5)',
                            hover: 'rgba(160, 160, 160 0.8)'
                        }
                    },
                    success: {
                        border: {
                            normal: 'rgba(92, 184, 92, 0.5)',
                            hover: 'rgba(92, 184, 92, 1)'
                        },
                        bg: {
                            normal: 'rgba(92, 184, 92, 0.5)',
                            hover: 'rgba(92, 184, 92, 0.8)'
                        }
                    },
                    info: {
                        border: {
                            normal: 'rgba(112, 187, 225, 0.5)',
                            hover: 'rgba(112, 187, 225, 1)'
                        },
                        bg: {
                            normal: 'rgba(112, 187, 225, 0.5)',
                            hover: 'rgba(112, 187, 225, 0.8)'
                        }
                    },
                    warning: {
                        border: {
                            normal: 'rgba(240, 173, 78, 0.5)',
                            hover: 'rgba(240, 173, 78, 1)'
                        },
                        bg: {
                            normal: 'rgba(240, 173, 78, 0.5)',
                            hover: 'rgba(240, 173, 78, 0.8)'
                        }
                    },
                    danger: {
                        border: {
                            normal: 'rgba(217, 83, 79, 0.5)',
                            hover: 'rgba(217, 83, 79, 1)'
                        },
                        bg: {
                            normal: 'rgba(217, 83, 79, 0.5)',
                            hover: 'rgba(217, 83, 79, 0.8)'
                        }
                    },
                    background: 'rgba(251, 85, 85, 0.8)'
                },
                tooltips: {
                    titleFontSize: 18,
                    bodyFontSize: 14
                },
                point: {
                    borderWidth: 2,
                    radius: {
                        normal: 5,
                        hover: 10
                    }
                },
                x: null,
                y: null
            },
            comparison: {
                type: 'bar', // horizontalBar
                height: 200,
                sets: null,
                active_set: null,
                a_row: null,
                b_row: null,

                row_id_field: 'name',
                a_color: '#43ac6a',
                b_color: '#f04124', //#b67102',
                colors: {
                    background: 'rgba(251, 85, 85, 0.8)'
                },
                tooltips: {
                    titleFontSize: 18,
                    bodyFontSize: 14
                }
            },
            element: {
                chart_mode_selector: '#chart_selector',
                bar: {
                    div: '#bar_div',
                    canvas: '#bar_canvas'
                },
                scatter: {
                    div: '#scatter_div',
                    canvas: 'scatter_canvas',
                    selector_x_menu: '#scatter_selector_x_menu',
                    selector_y_menu: '#scatter_selector_y_menu'
                },
                comparison: {
                    div: '#comparison_div',
                    canvas: '#comparison_canvas',

                    selector_set_button: '#comparison_selector_set_button',
                    selector_set_menu: '#comparison_selector_set_menu',

                    selector_a_button: '#comparison_selector_a_button',
                    selector_a_menu: '#comparison_selector_a_menu',

                    selector_b_button: '#comparison_selector_b_button',
                    selector_b_menu: '#comparison_selector_b_menu'
                }
            },
            icon: {
                bar: '<span class="glyphicon glyphicon-stats"></span>',
                scatter: '<span class="glyphicon glyphicon-th-large"></span>',
                comparison: '<span class="glyphicon glyphicon-align-left"></span>',
                off: '<span class="glyphicon glyphicon-off"></span>',
                caret: '<span class="caret"></span>',
                chartable: '<span class="glyphicon glyphicon-stats text-muted"></span>',
                url: '<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span>',
                ref: '<span class="glyphicon glyphicon-file" aria-hidden="true"></span>',
                anchor: '<span class="glyphicon glyphicon-expand" aria-hidden="true"></span>'
            },
            label: {
                bar: 'Bar',
                scatter: 'Scatter',
                comparison: 'Comparison',
                off: 'Off',
                select_x_axis: 'Select data field for X-axis',
                select_y_axis: 'Select data field for Y-axis',
                select_set: 'Select set',
                select_a_row: 'Select row A',
                select_b_row: 'Select row B'
            },
            button_css: {
                bar: 'btn btn-info selector-button',
                scatter: 'btn btn-info selector-button',
                comparison: 'btn btn-info selector-button',
                off: 'btn btn-default selector-button'
            },
            tags: {
                css: [
                    'label label-tag label-tag1',
                    'label label-tag label-tag2',
                    'label label-tag label-tag3',
                    'label label-tag label-tag4',
                    'label label-tag label-tag5',
                    'label label-tag label-tag6',
                    'label label-tag label-tag7',
                    'label label-tag label-tag8',
                    'label label-tag label-tag9',
                    'label label-tag label-tag10',
                    'label label-tag label-tag11',
                    'label label-tag label-tag12',
                    'label label-tag label-tag13',
                    'label label-tag label-tag14',
                    'label label-tag label-tag15',
                    'label label-tag label-tag-default',
                    'label label-tag label-tag-default',
                    'label label-tag label-tag-default',
                    'label label-tag label-tag-default',
                    'label label-tag label-tag-default',
                    'label label-tag label-tag-default',
                    'label label-tag label-default',
                    'label label-tag label-default',
                    'label label-tag label-default',
                    'label label-tag label-default',
                    'label label-tag label-primary',
                    'label label-tag label-success',
                    'label label-tag label-info',
                    'label label-tag label-warning',
                    'label label-tag label-danger'
                ]
            }
        },
        init: function (element, options, name) {
            this.name = name;                                             // Name of the plugin
            this.element = element;                                       // The DOM element that called the plugin
            this.$element = $(element);                                   // The DOM element that called the plugin inside jQuery object

            $(element).addClass(this.name);                               // Add plugin name as class

            // Take unique id
            this.uniqueId = window.datatable_uniqId;
            window.datatable_uniqId += 1; // increase id counter

            var attributes = jQuery.extend(true, {}, this.defaults);
            // Override defaults from data-*
            if (typeof $(element).data('show-chart') !== 'undefined') {
                attributes.show_chart = $(element).data('show-chart');
            }
            if (typeof $(element).data('row-highlighting') !== 'undefined') {
                attributes.table.row_highlighting = $(element).data('row-highlighting');
            }
            if (typeof $(element).data('id-field') !== 'undefined') {
                attributes.table.id_field = $(element).data('id-field');
            }
            if (typeof $(element).data('chart-modes') !== 'undefined') {
                attributes.chart_modes = $(element).data('chart-modes');
            }
            if (typeof $(element).data('chart-default-mode') !== 'undefined') {
                attributes.chart_default_mode = $(element).data('chart-default-mode');
            }
            if (typeof $(element).data('rank-mode') !== 'undefined') {
                attributes.rank_mode = $(element).data('rank-mode');
            }
            if (typeof $(element).data('tag-mode') !== 'undefined') {
                attributes.tag_mode = $(element).data('tag-mode');
            }
            if (typeof $(element).data('chart-position') !== 'undefined') {
                attributes.chart.position = $(element).data('chart-position');
            }
            // bar
            if (typeof $(element).data('bar-height') !== 'undefined') {
                attributes.bar.height = $(element).data('bar-height');
            }
            if (typeof $(element).data('bar-hline') !== 'undefined') {
                attributes.bar.hline.enabled = $(element).data('bar-hline');
            }
            if (typeof $(element).data('show-bar-chart-xaxis') !== 'undefined') {
                attributes.bar.show_bar_chart_xaxis = $(element).data('show-bar-chart-xaxis');
            }

            // scatter
            if (typeof $(element).data('scatter-height') !== 'undefined') {
                attributes.scatter.height = $(element).data('scatter-height');
            }
            if (typeof $(element).data('scatter-x') !== 'undefined') {
                attributes.scatter.x = $(element).data('scatter-x');
            }
            if (typeof $(element).data('scatter-y') !== 'undefined') {
                attributes.scatter.y = $(element).data('scatter-y');
            }

            // comparison
            if (typeof $(element).data('comparison-height') !== 'undefined') {
                attributes.comparison.height = $(element).data('comparison-height');
            }
            if (typeof $(element).data('comparison-row-id-field') !== 'undefined') {
                attributes.comparison.row_id_field = $(element).data('comparison-row-id-field');
            }
            if (typeof $(element).data('comparison-sets-json') !== 'undefined') {
                attributes.comparison.sets = $(element).data('comparison-sets-json');
            }
            if (typeof $(element).data('comparison-active-set') !== 'undefined') {
                attributes.comparison.active_set = $(element).data('comparison-active-set');
            }
            if (typeof $(element).data('comparison-a-row') !== 'undefined') {
                attributes.comparison.a_row = $(element).data('comparison-a-row');
            }
            if (typeof $(element).data('comparison-b-row') !== 'undefined') {
                attributes.comparison.b_row = $(element).data('comparison-b-row');
            }
            // Combine parameters from defaults and given plugin options.
            this.options = $.extend(true, {}, attributes, options);   // Plugin options object

            // Add bootstrap-table.js defaults if not set
            if (typeof $(element).data('page-list') === 'undefined') {
                $(this.element).attr('data-page-list', this.options.table.page_list);
            }
            if (typeof $(element).data('pagination') === 'undefined') {
                $(this.element).attr('data-pagination', this.options.table.pagination);
            }
            if (typeof $(element).data('show-header') === 'undefined') {
                $(this.element).attr('data-show-header', this.options.table.show_header);
            }
            if (typeof $(element).data('show-column') === 'undefined') {
                $(this.element).attr('data-show-column', this.options.table.show_column);
            }
            if (typeof $(element).data('show-pagination-switch') === 'undefined') {
                $(this.element).attr('data-show-pagination-switch', this.options.table.show_pagination_switch);
            }
            if (typeof $(element).data('striped') === 'undefined') {
                $(this.element).attr('data-striped', this.options.table.striped);
            }

            // Post-process options
            this.options.chart_modes = this.options.chart_modes.split(',');
            if (this.options.chart_modes.length == 1 && this.options.chart_modes[0] != this.options.chart_default_mode && this.options.chart_default_mode != 'off') {
                this.options.chart_default_mode = this.options.chart_modes[0];
            }

            // Initialization flag
            this.rank_init = true;
            this.comparison_init = true;

            this.table_rank = [];

            // Chart variables
            this.chart_update = false;
            this.chart_init = true;

            this.bar_chart = null;
            this.scatter_chart = null;
            this.comparison_chart = null;

            this.chart_mode = this.options.chart_default_mode;

            this.hline_value = [];
            this.hline_index = [];
            this.hline_color = [];

            this.field_meta = [];
            var self = this;

            this.log(this.name + '::init');
            var th_rank_element = $(this.element).find('thead tr th[data-rank="true"]');
            if (th_rank_element) {
                if (this.options.rank_mode.startsWith('grouped')) {
                    // Expose datatable rank generation for bootstrap-table.js
                    $(th_rank_element).attr('data-formatter', 'runningFormatterGrouped' + this.uniqueId);
                    window['runningFormatterGrouped' + this.uniqueId] = function (value, row, index) {
                        return $(self.element).datatable('getRank', index);
                    }
                } else if (this.options.rank_mode == 'normal') {
                    $(th_rank_element).attr('data-formatter', 'runningFormatter');
                }
            }

            // Add default sorter for columns marked sortable
            $(this.element).find('thead tr th[data-sortable="true"]').each(function () {
                $(this).attr('data-sorter', 'valueSorter');
            });
            // Add default sorter for columns marked sortable
            $(this.element).find('thead tr th[data-reversed="true"]').each(function () {
                $(this).attr('data-sorter', 'valueSorterReverse');
            });

            // Add chartable icon for columns marked chartable
            $(this.element).find('thead tr th[data-chartable="true"]').each(function () {
                $(this).append(' ' + self.options.icon.chartable);
            });

            $(this.element).find('thead tr th[data-value-type]').each(function () {
                var value_type = $(this).data('value-type');

                if (value_type.includes('percentage')) {
                    $(this).attr('data-postfix', '%');
                }

                switch (value_type) {
                    case 'int':
                        $(this).attr('data-formatter', 'valueFormatter_int');
                        break;
                    case 'float1':
                        $(this).attr('data-formatter', 'valueFormatter_float1');
                        break;
                    case 'float2':
                        $(this).attr('data-formatter', 'valueFormatter_float2');
                        break;
                    case 'float3':
                        $(this).attr('data-formatter', 'valueFormatter_float3');
                        break;
                    case 'float4':
                        $(this).attr('data-formatter', 'valueFormatter_float4');
                        break;
                    case 'int-percentage':
                        $(this).attr('data-formatter', 'valueFormatter_int_percentage');
                        break;
                    case 'float1-percentage':
                        $(this).attr('data-formatter', 'valueFormatter_float1_percentage');
                        break;
                    case 'float2-percentage':
                        $(this).attr('data-formatter', 'valueFormatter_float2_percentage');
                        break;
                    case 'float3-percentage':
                        $(this).attr('data-formatter', 'valueFormatter_float3_percentage');
                        break;
                    case 'float4-percentage':
                        $(this).attr('data-formatter', 'valueFormatter_float4_percentage');
                        break;
                    case 'list':
                        $(this).attr('data-formatter', 'valueFormatter_list');
                        break;
                    case 'url':
                        window['valueFormatter_url' + this.uniqueId] = function (value, row, index) {
                            var links = [];
                            if (value.trim()) {
                                var items = value.split(',');
                                if (items.length > 0) {
                                    for (var i = 0; i < items.length; i++) {
                                        var current_link = items[i].trim();
                                        var index = current_link.lastIndexOf(';');
                                        if (index != -1) {
                                            var link = current_link.substring(0, index);
                                            var link_title = current_link.substring(index + 1);
                                            links.push('<a class="datatable-link" href="' + link + '">' + link_title + '</a>');
                                        } else {
                                            links.push('<a class="datatable-icon" href="' + current_link + '">' + self.options.icon.url + '</a>');
                                        }
                                    }
                                }
                            }
                            return links.join('<br>');
                        };
                        $(this).attr('data-formatter', 'valueFormatter_url' + this.uniqueId);
                        break;
                    case 'ref':
                        window['valueFormatter_ref' + this.uniqueId] = function (value, row, index) {
                            var links = [];
                            if (value.trim()) {
                                var items = value.split(',');
                                if (items.length > 0) {
                                    for (var i = 0; i < items.length; i++) {
                                        var current_link = items[i].trim();
                                        var index = current_link.lastIndexOf(';');
                                        if (index != -1) {
                                            var link = current_link.substring(0, index);
                                            var link_title = current_link.substring(index + 1);
                                            links.push('<a class="datatable-link" href="' + link + '">[' + link_title + ']</a>');
                                        } else {
                                            links.push('<a class="datatable-icon" href="' + current_link + '">' + self.options.icon.ref + '</a>');
                                        }
                                    }
                                }
                            }
                            return links.join('<br>');
                        };
                        $(this).attr('data-formatter', 'valueFormatter_ref' + this.uniqueId);
                        break;
                    case 'anchor':
                        window['valueFormatter_anchor' + this.uniqueId] = function valueFormatter_anchor(value, row, index) {
                            if (value.trim()) {
                                return '<a class="datatable-icon" href="javascript:void(0)" onclick="$(\'#collapse-' + value + '\').collapse(\'show\');window.location.hash=\'' + value + '\';return false;">' + self.options.icon.anchor + '</a>'
                            } else {
                                return value;
                            }
                        };

                        $(this).attr('data-formatter', 'valueFormatter_anchor' + this.uniqueId);
                        break;
                }
            });

            if (this.options.show_chart) {
                // Extend bar plot to have horizontal line
                var originalLineDraw = Chart.controllers.bar.prototype.draw;
                Chart.helpers.extend(Chart.controllers.bar.prototype, {
                    draw: function () {
                        originalLineDraw.apply(this, arguments);
                        var chart = this.chart;
                        var scale = this.scale;
                        var ctx = chart.chart.ctx;
                        if (chart.config.data.hline_enabled) {
                            var hline_index = chart.config.data.hline_index;
                            var hline_value = chart.config.data.hline_value;
                            var hline_color = chart.config.data.hline_color;
                            $.each(hline_value, function (index, value) {
                                var xaxis = chart.scales['x-axis-0'];
                                var yaxis = chart.scales['y-axis-0'];
                                var y = yaxis.getPixelForValue(value);
                                ctx.save();
                                ctx.beginPath();
                                ctx.strokeStyle = hline_color[index];
                                ctx.lineWidth = self.options.bar.hline.lineWidth;
                                ctx.moveTo(xaxis.left, y);
                                ctx.lineTo(xaxis.right, y);
                                ctx.stroke();
                                ctx.restore();
                            });
                        }
                    }
                });

                // Create toolbar html and add it to the DOM
                var toolbar_html = '';

                toolbar_html += '<div id="datatable_toolbar' + this.uniqueId + '" class="datatable-toolbar">';
                toolbar_html += '<div class="btn-group" data-toggle="buttons" id="chart_mode_selector' + this.uniqueId + '">';

                // Bar chart selection button
                if (this.options.chart_modes.indexOf('bar') > -1) {
                    var active = '';
                    if (this.options.chart_default_mode == 'bar') {
                        active = ' active ';
                    }
                    toolbar_html += '<label class="' + this.options.button_css.bar + active + '" data-mode="bar">';
                    toolbar_html += '<input autocomplete="off" data-mode="bar" name="options" type="radio" value="bar">' + this.options.icon.bar + ' ' + this.options.label.bar + '</input>';
                    toolbar_html += '</label>';
                }

                // Scatter chart selection button
                if (this.options.chart_modes.indexOf('scatter') > -1) {
                    var active = '';
                    if (this.options.chart_default_mode == 'scatter') {
                        active = ' active ';
                    }
                    toolbar_html += '<label class="' + this.options.button_css.scatter + active + '" data-mode="scatter">';
                    toolbar_html += '<input autocomplete="off" data-mode="scatter" name="options" type="radio" value="scatter">' + this.options.icon.scatter + ' ' + this.options.label.scatter + '</input>';
                    toolbar_html += '</label>';
                }

                // Comparison chart selection button
                if (this.options.chart_modes.indexOf('comparison') > -1) {
                    var active = '';
                    if (this.options.chart_default_mode == 'comparison') {
                        active = ' active ';
                    }
                    toolbar_html += '<label class="' + this.options.button_css.comparison + active + '" data-mode="comparison">';
                    toolbar_html += '<input autocomplete="off" data-mode="comparison" name="options" type="radio" value="comparison">' + this.options.icon.comparison + ' ' + this.options.label.comparison + '</input>';
                    toolbar_html += '</label>';
                }

                // Off button
                var active = '';
                if (this.options.chart_default_mode == 'off') {
                    active = ' active ';
                }
                toolbar_html += '<label class="' + this.options.button_css.off + active + '" data-mode="off">';
                toolbar_html += '<input autocomplete="off" checked data-mode="off" name="options" type="radio" value="off">' + this.options.icon.off + ' ' + this.options.label.off + '</input>';
                toolbar_html += '</label>';
                toolbar_html += '</div>';
                toolbar_html += '</div>';

                var $div = $('<div>', {id: 'datatable_visualization' + this.uniqueId, 'class': 'datatable'});

                // Bar
                if (this.options.chart_modes.indexOf('bar') > -1) {
                    var bar_div = '<div id="bar_div' + this.uniqueId + '" style="display:none;"><canvas id="bar_chart' + this.uniqueId + '"></canvas></div>';
                    this.options.element.bar.div = '#bar_div' + this.uniqueId;
                    this.options.element.bar.canvas = '#bar_chart' + this.uniqueId;
                    $($div).append(bar_div);
                }

                // Scatter
                if (this.options.chart_modes.indexOf('scatter') > -1) {
                    var scatter_div = '<div id="scatter_div' + this.uniqueId + '" style="display:none;">';
                    scatter_div += '<canvas id="scatter_chart' + this.uniqueId + '"></canvas>';
                    scatter_div += '<div class="btn-group">';
                    // X-selector
                    scatter_div += '<div class="btn-group">';
                    scatter_div += '<div class="dropdown">';
                    scatter_div += '<button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="scatter_selector_x' + this.uniqueId + '" type="button">X ' + this.options.icon.caret + '</button>';
                    scatter_div += '<ul aria-labelledby="scatter_selector_x' + this.uniqueId + '" class="dropdown-menu" id="scatter_selector_x_menu' + this.uniqueId + '" role="menu">';
                    scatter_div += '<li class="dropdown-header">' + this.options.label.select_x_axis + '</li>';
                    $(this.element).find('thead tr th[data-chartable="true"]').each(function () {
                        var postfix = '';
                        if (typeof $(this).data('postfix') !== 'undefined') {
                            postfix = $(this).data('postfix');
                        }
                        var reversed = false;
                        if (typeof $(this).data('reversed') !== 'undefined') {
                            reversed = $(this).data('reversed');
                        }
                        if (typeof $(this).data('field') !== 'undefined') {
                            var field = $(this).data('field');
                        }
                        var active = '';
                        var selected = '0';
                        if (field == self.options.scatter.x) {
                            active = ' class="active" ';
                            selected = '1';
                        }
                        scatter_div += '<li' + active + '><a data-field="' + field + '" data-postfix="' + postfix + '" data-reversed="' + reversed + '" data-selected="' + selected + '" href="#" onclick="return false;">' + $.trim($(this).text()) + '</a></li>'
                    });
                    scatter_div += '</ul>';
                    scatter_div += '</div>';
                    scatter_div += '</div>';

                    // Y-selector
                    scatter_div += '<div class="btn-group">';
                    scatter_div += '<div class="dropdown">';
                    scatter_div += '<button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="scatter_selector_y' + this.uniqueId + '" type="button">Y ' + this.options.icon.caret + '</button>';
                    scatter_div += '<ul aria-labelledby="scatter_selector_y' + this.uniqueId + '" class="dropdown-menu" id="scatter_selector_y_menu' + this.uniqueId + '" role="menu">';
                    scatter_div += '<li class="dropdown-header">' + this.options.label.select_y_axis + '</li>';
                    $(this.element).find('thead tr th[data-chartable="true"]').each(function () {
                        var postfix = '';
                        if (typeof $(this).data('postfix') !== 'undefined') {
                            postfix = $(this).data('postfix');
                        }
                        var reversed = false;
                        if (typeof $(this).data('reversed') !== 'undefined') {
                            reversed = $(this).data('reversed');
                        }
                        if (typeof $(this).data('field') !== 'undefined') {
                            var field = $(this).data('field');
                        }
                        var active = '';
                        var selected = '0';
                        if (field == self.options.scatter.y) {
                            active = ' class="active" ';
                            selected = '1';
                        }
                        scatter_div += '<li' + active + '><a data-field="' + field + '" data-postfix="' + postfix + '" data-reversed="' + reversed + '" data-selected="' + selected + '" href="#" onclick="return false;">' + $.trim($(this).text()) + '</a></li>'
                    });
                    scatter_div += '</ul>';
                    scatter_div += '</div>';
                    scatter_div += '</div>';

                    scatter_div += '</div>';

                    scatter_div += '</div>';
                    this.options.element.scatter.div = '#scatter_div' + this.uniqueId;
                    this.options.element.scatter.canvas = '#scatter_chart' + this.uniqueId;
                    this.options.element.scatter.selector_x_menu = '#scatter_selector_x_menu' + this.uniqueId;
                    this.options.element.scatter.selector_y_menu = '#scatter_selector_y_menu' + this.uniqueId;
                    $($div).append(scatter_div);

                    // X
                    $(document).on("click", this.options.element.scatter.selector_x_menu + ' li a', function () {
                        $(self.options.element.scatter.selector_x_menu + ' li a').each(function () {
                            $(this).data('selected', 0);
                            $(this).parent().removeClass('active');
                        });
                        $(this).data('selected', 1);
                        $(this).parent().addClass('active');
                        self.updateScatter();
                    });

                    // Y
                    $(document).on("click", this.options.element.scatter.selector_y_menu + ' li a', function () {
                        $(self.options.element.scatter.selector_y_menu + ' li a').each(function () {
                            $(this).data('selected', 0);
                            $(this).parent().removeClass('active');
                        });
                        $(this).data('selected', 1);
                        $(this).parent().addClass('active');
                        self.updateScatter();
                    });
                }

                // Comparison
                if (this.options.chart_modes.indexOf('comparison') > -1) {
                    var comparison_div = '<div id="comparison_div' + this.uniqueId + '" style="display:none;">';
                    comparison_div += '<canvas id="comparison_chart' + this.uniqueId + '"></canvas>';
                    comparison_div += '<div class="btn-group" style="margin-right:10px;">';

                    // Set selector
                    comparison_div += '<div class="dropdown">';
                    if (this.options.comparison.sets) {
                        for (var i = 0; i < this.options.comparison.sets.length; i++) {
                            var set = this.options.comparison.sets[i];
                            if (set.title == self.options.comparison.active_set) {
                                comparison_div += '<button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle selector-button-xlarge" data-toggle="dropdown" id="comparison_selector_set' + this.uniqueId + '" type="button">' + set.title + ' ' + this.options.icon.caret + '</button>';
                                break;
                            }
                        }
                    } else {
                        comparison_div += '<button aria-expanded="false" aria-haspopup="true" class="btn btn-default dropdown-toggle selector-button-xlarge" data-toggle="dropdown" id="comparison_selector_set' + this.uniqueId + '" type="button">' + this.options.icon.caret + '</button>';
                    }

                    comparison_div += '<ul aria-labelledby="comparison_selector_set" class="dropdown-menu" id="comparison_selector_set_menu' + this.uniqueId + '" role="menu">';
                    comparison_div += '<li class="dropdown-header">' + this.options.label.select_set + '</li>';
                    if (this.options.comparison.sets) {
                        for (var i = 0; i < this.options.comparison.sets.length; i++) {
                            var set = this.options.comparison.sets[i];
                            var active = '';
                            var selected = '0';
                            if (set.title == self.options.comparison.active_set) {
                                active = ' class="active" ';
                                selected = '1';
                            }
                            comparison_div += '<li' + active + '><a data-selected="' + selected + '" data-set="' + set.title + '" href="#" onclick="return false;">' + set.title + '</a></li>';
                        }
                    }
                    comparison_div += '</ul>';
                    comparison_div += '</div>';
                    comparison_div += '</div>';

                    comparison_div += '<div class="btn-group" role="group">';
                    // Row A
                    comparison_div += '<div class="btn-group">';
                    comparison_div += '<div class="dropdown">';
                    comparison_div += '<button class="btn btn-success dropdown-toggle selector-button-large" data-toggle="dropdown" id="comparison_selector_a' + this.uniqueId + '" type="button" aria-expanded="false" aria-haspopup="true">' + this.options.icon.caret + '</button>';
                    comparison_div += '<ul aria-labelledby="comparison_selector_a' + this.uniqueId + '" class="dropdown-menu" id="comparison_selector_a_menu' + this.uniqueId + '" role="menu">';
                    comparison_div += '<li class="dropdown-header">' + this.options.label.select_a_row + '</li>';
                    comparison_div += '</ul>';
                    comparison_div += '</div>';
                    comparison_div += '</div>';

                    // Row B
                    comparison_div += '<div class="btn-group">';
                    comparison_div += '<div class="dropdown">';
                    comparison_div += '<button class="btn btn-danger dropdown-toggle selector-button-large" data-toggle="dropdown" id="comparison_selector_b' + this.uniqueId + '" type="button" aria-expanded="false" aria-haspopup="true">' + this.options.icon.caret + '</button>';
                    comparison_div += '<ul aria-labelledby="comparison_selector_b' + this.uniqueId + '" class="dropdown-menu" id="comparison_selector_b_menu' + this.uniqueId + '" role="menu">';
                    comparison_div += '<li class="dropdown-header">' + this.options.label.select_b_row + '</li>';
                    comparison_div += '</ul>';
                    comparison_div += '</div>';
                    comparison_div += '</div>';

                    comparison_div += '</div>';

                    comparison_div += '</div>';

                    this.options.element.comparison.div = '#comparison_div' + this.uniqueId;
                    this.options.element.comparison.canvas = '#comparison_chart' + this.uniqueId;

                    this.options.element.comparison.selector_set_button = '#comparison_selector_set' + this.uniqueId;
                    this.options.element.comparison.selector_set_menu = '#comparison_selector_set_menu' + this.uniqueId;

                    this.options.element.comparison.selector_a_button = '#comparison_selector_a' + this.uniqueId;
                    this.options.element.comparison.selector_a_menu = '#comparison_selector_a_menu' + this.uniqueId;

                    this.options.element.comparison.selector_b_button = '#comparison_selector_b' + this.uniqueId;
                    this.options.element.comparison.selector_b_menu = '#comparison_selector_b_menu' + this.uniqueId;

                    $($div).append(comparison_div);

                    // Set selector
                    $(this.options.element.comparison.selector_set_menu).on('click', ' li a', function (e) {
                        $(self.options.element.comparison.selector_set_menu + ' li a').each(function () {
                            $(this).data('selected', 0);
                            $(this).parent().removeClass('active');
                        });
                        $(this).data('selected', 1);
                        $(this).parent().addClass('active');
                        $(self.options.element.comparison.selector_set_button).html($(this).text() + ' ' + self.options.icon.caret);
                        self.updateComparison();
                    });

                    // Row selector, A
                    $(this.options.element.comparison.selector_a_menu).on('click', 'li a', function (e) {
                        $(self.options.element.comparison.selector_a_menu + ' li a').each(function () {
                            $(this).data('selected', 0);
                            $(this).parent().removeClass('active');
                        });
                        $(this).data('selected', 1);
                        $(this).parent().addClass('active');
                        $(self.options.element.comparison.selector_a_button).html($(this).text() + ' ' + self.options.icon.caret);

                        self.updateComparison();
                    });

                    // Row selector, B
                    $(this.options.element.comparison.selector_b_menu).on('click', 'li a', function (e) {
                        $(self.options.element.comparison.selector_b_menu + ' li a').each(function () {
                            $(this).data('selected', 0);
                            $(this).parent().removeClass('active');
                        });
                        $(this).data('selected', 1);
                        $(this).parent().addClass('active');
                        $(self.options.element.comparison.selector_b_button).html($(this).text() + ' ' + self.options.icon.caret);
                        self.updateComparison();
                    });
                }

                if (this.options.chart.position == 'top') {
                    $(toolbar_html).appendTo($div);
                    $div.insertBefore(this.$element);
                    //$(toolbar_html).insertBefore(this.element);
                } else if (this.options.chart.position == 'bottom') {
                    $div.insertAfter(this.$element);
                    $(toolbar_html).prependTo($div);

                    //$(toolbar_html).insertAfter(this.element);
                }
                this.$element.attr('data-toolbar', '#datatable_toolbar' + this.uniqueId);
                this.options.element.chart_mode_selector = '#chart_mode_selector' + this.uniqueId;
            }

            // Event handlers
            $(element).on('post-init.datatable', function (e, data) {
                // Datatable is ready to initialize bootstrap table
                self.initBootstrapTable();
            });
            $(element).on('post-body.bs.table', function (e, data) {

                $(self.options.element.chart_mode_selector + ' .active').each(function () {
                    self.chart_mode = $(this).data('mode');
                    self.updateVisualizationVisibility(self.chart_mode);
                    return false;
                });
                if (self.options.rank_mode.startsWith('grouped')) {
                    if (self.rank_init) {
                        setTimeout(function () {
                            self.updateRank();
                            self.rank_init = false;
                        }, 100);
                    }
                }
                if (self.options.chart_modes.indexOf('comparison') > -1) {
                    if (self.comparison_init) {
                        setTimeout(function () {
                            self.initComparison();
                        }, 100);
                    }
                }
                if (self.chart_update || self.chart_init) {
                    if (self.chart_init) {
                        setTimeout(function () {
                            self.updateChart();
                            self.chart_init = false;
                        }, 100);
                    } else {
                        self.updateChart();
                    }
                    self.chart_update = false;
                }

            });
            $(element).on('sort.bs.table', function (e, name, order) {
                if (self.options.rank_mode.startsWith('grouped')) {
                    setTimeout(function () {
                        self.updateRank();
                    }, 100);
                }
                self.chart_update = true;

            });
            $(element).on('search.bs.table', function (e, name, order) {
                if (self.options.rank_mode.startsWith('grouped')) {
                    setTimeout(function () {
                        self.updateRank();
                    }, 100);
                }
                self.chart_update = true;

            });

            // Visualization selector buttons
            $(this.options.element.chart_mode_selector + ' label').on('click', function () {
                self.chart_mode = $(this).data('mode');
                self.updateVisualizationVisibility(self.chart_mode);
            });

            this.updateFieldMeta();

            if (typeof $(element).data('json') !== 'undefined') {
                // Populate table from json data file
                var json_datafile = $(element).data('json');
                jQuery.ajax({
                    type: 'GET',
                    mimeType: "application/json; charset=utf-8",
                    url: json_datafile,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        var $thead = $(self.element).find('thead');
                        var $tbody = $('<tbody></tbody>');

                        $.each(data, function (key, val) {
                            var $row = $('<tr></tr>');
                            if (val.hline) {
                                $row.attr('data-hline', true);
                            }
                            if (self.options.table.row_highlighting) {
                                if (val.row_css == 'active') {
                                    $row.addClass('active');
                                } else if (val.row_css == 'success') {
                                    $row.addClass('success');
                                } else if (val.row_css == 'info') {
                                    $row.addClass('info');
                                } else if (val.row_css == 'warning') {
                                    $row.addClass('warning');
                                } else if (val.row_css == 'danger') {
                                    $row.addClass('danger');
                                }
                            }
                            $.each(self.field_meta, function (header_key, header_val) {
                                $($row).append($('<td></td>').text(val[header_val.field]));
                            });
                            $($tbody).append($row);
                        });
                        $($tbody).insertAfter($thead);
                        self.updateTags();
                        $(self.element).trigger('post-init.datatable');
                    },
                    error: function () {
                        console.log('ERROR reading file:' + json_datafile);
                    }
                });

            } else if (typeof $(element).data('yaml') !== 'undefined') {
                // Populate table from yaml data file
                var yaml_datafile = $(element).data('yaml');

                jQuery.ajax({
                    type: 'GET',
                    dataType: 'text',
                    mimeType: "application/yaml; charset=utf-8",
                    url: yaml_datafile,
                    async: true,
                    success: function (text) {
                        try {
                            var data = jsyaml.safeLoad(text);
                            var $thead = $(self.element).find('thead');
                            var $tbody = $('<tbody></tbody>');
                            $.each(data.data, function (key, val) {
                                var $row = $('<tr></tr>');
                                if (val.hline) {
                                    $row.attr('data-hline', true);
                                }
                                if (self.options.table.row_highlighting) {
                                    if (val.row_css == 'active') {
                                        $row.addClass('active');
                                    } else if (val.row_css == 'success') {
                                        $row.addClass('success');
                                    } else if (val.row_css == 'info') {
                                        $row.addClass('info');
                                    } else if (val.row_css == 'warning') {
                                        $row.addClass('warning');
                                    } else if (val.row_css == 'danger') {
                                        $row.addClass('danger');
                                    }
                                }
                                $.each(self.field_meta, function (header_key, header_val) {
                                    $($row).append($('<td></td>').text(val[header_val.field]));
                                });
                                $($tbody).append($row);
                            });
                            $($tbody).insertAfter($thead);
                            self.updateTags();
                            $(self.element).trigger('post-init.datatable');
                        } catch (e) {
                            console.log(e);
                        }
                    },
                    error: function () {
                        console.log('ERROR reading file:' + yaml_datafile);
                    }
                });
            } else {
                // we are ready
                self.updateTags();
                $(self.element).trigger('post-init.datatable');
            }
        },
        updateFieldMeta: function () {
            var self = this;
            this.field_meta = [];
            $(this.element).find('thead tr th').each(function () {
                var meta = {
                    field: null,
                    rank: null,
                    chartable: null,
                    sortable: null,
                    visible: null,
                    beginatzero: null,
                    align: null,
                    tag: null,
                    filter_control: null,
                    formatter: null,
                    postfix: null
                };
                if (typeof $(this).data('field') !== 'undefined') {
                    meta.field = $(this).data('field');
                }
                if (typeof $(this).data('rank') !== 'undefined') {
                    meta.rank = $(this).data('rank');
                }
                if (typeof $(this).data('chartable') !== 'undefined') {
                    meta.chartable = $(this).data('chartable');
                }
                if (typeof $(this).data('sortable') !== 'undefined') {
                    meta.sortable = $(this).data('sortable');
                }
                if (typeof $(this).data('visible') !== 'undefined') {
                    meta.visible = $(this).data('visible');
                }
                if (typeof $(this).data('beginatzero') !== 'undefined') {
                    meta.beginatzero = $(this).data('beginatzero');
                }
                if (typeof $(this).data('align') !== 'undefined') {
                    meta.align = $(this).data('align');
                }
                if (typeof $(this).data('tag') !== 'undefined') {
                    meta.tag = $(this).data('tag');
                }
                if (typeof $(this).data('filter-control') !== 'undefined') {
                    meta.filter_control = $(this).data('filter-control');
                }
                if (typeof $(this).data('formatter') !== 'undefined') {
                    meta.formatter = $(this).data('formatter');
                }
                if (typeof $(this).data('postfix') !== 'undefined') {
                    meta.postfix = $(this).data('postfix');
                }
                if (meta.field != null || meta.rank) {
                    self.field_meta.push(meta);
                }
            });
        },

        updateVisualizationVisibility: function (mode) {
            if (mode == 'off') {
                if (this.options.chart_modes.indexOf('bar') > -1) {
                    this.hideBarChart();
                }
                if (this.options.chart_modes.indexOf('scatter') > -1) {
                    this.hideScatterChart();
                }
                if (this.options.chart_modes.indexOf('comparison') > -1) {
                    this.hideComparisonChart();
                }
            } else if (mode == 'bar') {

                if (this.options.chart_modes.indexOf('scatter') > -1) {
                    this.hideScatterChart();
                }
                if (this.options.chart_modes.indexOf('comparison') > -1) {
                    this.hideComparisonChart();
                }
                this.showBarChart();

            } else if (mode == 'scatter') {
                if (this.options.chart_modes.indexOf('bar') > -1) {
                    this.hideBarChart();
                }
                if (this.options.chart_modes.indexOf('comparison') > -1) {
                    this.hideComparisonChart();
                }
                this.showScatterChart();

            } else if (mode == 'comparison') {
                if (this.options.chart_modes.indexOf('bar') > -1) {
                    this.hideBarChart();
                }
                if (this.options.chart_modes.indexOf('scatter') > -1) {
                    this.hideScatterChart();
                }
                this.showComparisonChart();
            }
        },
        initBootstrapTable: function () {
            // Load bootstrap-table
            $(this.element).bootstrapTable();
            $(this.element).trigger('post-body.bs.table');
        },
        updateChart: function () {
            if (this.options.show_chart) {
                if (this.chart_mode == 'off') {

                } else if (this.chart_mode == 'bar') {
                    this.updateBar();
                } else if (this.chart_mode == 'scatter') {
                    this.updateScatter();
                } else if (this.chart_mode == 'comparison') {
                    this.updateComparison();
                }
            }
        },
        validBar: function () {
            var table_options = $(this.element).bootstrapTable('getOptions');
            var sort_name = table_options.sortName;
            var header = $(this.element).find('thead tr th[data-field=\"' + sort_name + '\"]');
            var chartable = false;
            if (typeof header.data('chartable') !== 'undefined') {
                chartable = header.data('chartable');
            }
            var table_data = $(this.element).bootstrapTable('getData');
            if (table_data.length > 0 && chartable) {
                return true;
            } else {
                return false;
            }
        },
        updateBar: function () {
            if (this.bar_chart) {
                this.bar_chart.destroy();
            }

            var chart_ctx = document.getElementById(this.options.element.bar.canvas.replace('#', ''));
            chart_ctx.width = $(this.options.element.bar.div).innerWidth();
            chart_ctx.height = this.options.bar.height;
            var table_options = $(this.element).bootstrapTable('getOptions');
            var table_data = $(this.element).bootstrapTable('getData');
            var sort_name = table_options.sortName;
            var id_field = table_options.idField;
            var header = $(this.element).find('thead tr th[data-field=\"' + sort_name + '\"]');
            var chartable = false;
            if (typeof header.data('chartable') !== 'undefined') {
                chartable = header.data('chartable');
            }
            var value_postfix = '';
            if (typeof header.data('postfix') !== 'undefined') {
                value_postfix = header.data('postfix');
            }
            var value_label = header.text().trim();

            var labels = [];
            var data = [];
            var border_colors = [];
            var border_hover_colors = [];
            var bg_colors = [];
            var bg_hover_colors = [];

            this.hline_value = [];
            this.hline_color = [];
            this.hline_index = [];

            for (var i = 0; i < table_data.length; i++) {
                // Go through the table data and collect values and colors for bar plot.
                var label = $("<div/>").html(table_data[i][this.options.table.id_field]).text().trim().replace(/<(?:.|\n)*?>/gm, '').replace('_', ' ');

                // Make sure labels are unique, if overlapping labels add whitespaces at the end.
                if (labels.indexOf(label) > -1) {
                    var num_of_occurances = 0;
                    for (var i = 0; i < labels.length; i++) {
                        if (labels[i] === label)
                            num_of_occurances++;
                    }
                    label = label + new Array(num_of_occurances + 1).join(' ');
                }
                labels.push(label);

                // Convert value to float
                var value = parseFloat(table_data[i][sort_name]);
                data.push(value);

                if (table_data[i].hasOwnProperty('_data') && table_data[i]['_data'].hasOwnProperty('hline') && typeof table_data[i]['_data']['hline'] !== 'undefined' && table_data[i]['_data']['hline']) {
                    this.hline_value.push(parseFloat(table_data[i][sort_name]));
                    this.hline_index.push(i);
                    if (table_data[i].hasOwnProperty('_class') && typeof table_data[i]['_class'] !== 'undefined') {
                        this.hline_color.push(this.options.bar.colors[table_data[i]['_class']].bg.normal);
                    } else {
                        this.hline_color.push(this.options.bar.colors.default.bg.normal);
                    }
                }

                if (table_data[i].hasOwnProperty('_class') && typeof table_data[i]['_class'] !== 'undefined') { // we have color defined
                    if (this.options.bar.colors.valid.indexOf(table_data[i]['_class']) > -1) {
                        border_colors.push(this.options.bar.colors[table_data[i]['_class']].border.normal);
                        border_hover_colors.push(this.options.bar.colors[table_data[i]['_class']].border.hover);
                        bg_colors.push(this.options.bar.colors[table_data[i]['_class']].bg.normal);
                        bg_hover_colors.push(this.options.bar.colors[table_data[i]['_class']].border.hover);
                    } else {
                        border_colors.push(this.options.bar.colors.default.border.normal);
                        border_hover_colors.push(this.options.bar.colors.default.border.hover);
                        bg_colors.push(this.options.bar.colors.default.bg.normal);
                        bg_hover_colors.push(this.options.bar.colors.default.bg.hover);
                    }
                } else {
                    border_colors.push(this.options.bar.colors.default.border.normal);
                    border_hover_colors.push(this.options.bar.colors.default.border.hover);
                    bg_colors.push(this.options.bar.colors.default.bg.normal);
                    bg_hover_colors.push(this.options.bar.colors.default.bg.hover);
                }
            }

            if (chartable && data.length > 0) {
                $(this.options.element.bar.div).show(0);
                var begin_at_zero = $(this.element).find('thead tr th[data-field=\"' + sort_name + '\"]').data('beginatzero');
                var chart_data = {
                    labels: labels,
                    datasets: [
                        {
                            label: sort_name,
                            backgroundColor: bg_colors,
                            borderColor: border_colors,
                            borderWidth: 2,
                            hoverBackgroundColor: bg_hover_colors,
                            hoverBorderColor: border_hover_colors,
                            data: data
                        }
                    ],
                    hline_index: this.hline_index,
                    hline_value: this.hline_value,
                    hline_color: this.hline_color,
                    hline_enabled: this.options.bar.hline.enabled
                };
                var chart_options = {
                    scales: {
                        xAxes: [{
                            display: this.options.bar.show_bar_chart_xaxis,
                            stacked: false,
                            ticks: {
                                autoSkip: false,
                                maxRotation: 90
                            }
                        }],
                        yAxes: [{
                            stacked: false,
                            type: 'linear',
                            ticks: {
                                beginAtZero: begin_at_zero
                            },
                            scaleLabel: {
                                display: true,
                                labelString: value_label,
                                fontColor: 'black'
                            }
                        }]
                    },
                    maintainAspectRatio: true,
                    responsive: true,
                    animation: {
                        duration: 100
                    },
                    title: {
                        display: false
                    },
                    legend: {
                        display: false
                    },
                    tooltips: {
                        enabled: true,
                        mode: 'label',
                        titleFontSize: this.options.bar.tooltips.titleFontSize,
                        bodyFontSize: this.options.bar.tooltips.bodyFontSize,
                        callbacks: {
                            title: function (tooltipItem, data) {
                                return data.labels[tooltipItem[0].index];
                            },
                            label: function (tooltipItem, data) {
                                return value_label + ': ' + data.datasets[0].data[tooltipItem.index] + value_postfix;
                            }
                        }
                    },
                    chartArea: {
                        backgroundColor: this.options.bar.colors.background
                    }
                };
                this.bar_chart = new Chart(chart_ctx, {
                    type: 'bar',
                    data: chart_data,
                    options: chart_options
                });
            } else {
                if ($(this.options.element.bar.div).is(":visible")) {
                    $(this.options.element.bar.div).hide(0);
                }
            }
        },
        updateScatter: function () {
            if (this.scatter_chart) {
                this.scatter_chart.destroy();
            }
            var x_field = '';
            var x_label = '';
            var x_postfix = '';
            var x_scale_reverse = false;
            var y_field = '';
            var y_label = '';
            var y_postfix = '';
            var y_scale_reverse = false;
            $(this.options.element.scatter.selector_x_menu + ' li a').each(function () {
                if ($(this).data('selected') == 1) {
                    x_field = $(this).data('field');
                    x_postfix = ' ' + $(this).data('postfix');
                    x_scale_reverse = $(this).data('reversed');
                    x_label = $(this).text();
                    return false;
                }
            });
            $(this.options.element.scatter.selector_y_menu + ' li a').each(function () {
                if ($(this).data('selected') == 1) {
                    y_field = $(this).data('field');
                    y_postfix = ' ' + $(this).data('postfix');
                    y_scale_reverse = $(this).data('reversed');
                    y_label = $(this).text();
                    return false;
                }
            });
            var table_options = $(this.element).bootstrapTable('getOptions');
            var table_data = $(this.element).bootstrapTable('getData');
            var sort_name = table_options.sortName;
            var id_field = table_options.idField;
            var chartable = $(this.element).find('thead tr th[data-field=\"' + sort_name + '\"]').data('chartable');
            var labels = [];
            var data = [];
            var border_colors = [];
            var border_hover_colors = [];
            var bg_colors = [];
            var bg_hover_colors = [];
            var point_radius = [];
            var point_hover_radius = [];
            for (var i = 0; i < table_data.length; i++) {
                labels.push($("<div/>").html(table_data[i][this.options.table.id_field]).text().replace(/<(?:.|\n)*?>/gm, ''));
                if (table_data[i].hasOwnProperty('_class') && typeof table_data[i]['_class'] !== 'undefined') {
                    if (this.options.scatter.colors.valid.indexOf(table_data[i]['_class']) > -1) {
                        border_colors.push(this.options.scatter.colors[table_data[i]['_class']].border.normal);
                        border_hover_colors.push(this.options.scatter.colors[table_data[i]['_class']].border.hover);
                        bg_colors.push(this.options.scatter.colors[table_data[i]['_class']].bg.normal);
                        bg_hover_colors.push(this.options.scatter.colors[table_data[i]['_class']].border.hover);
                    } else {
                        border_colors.push(this.options.scatter.colors.default.border.normal);
                        border_hover_colors.push(this.options.scatter.colors.default.border.hover);
                        bg_colors.push(this.options.scatter.colors.default.bg.normal);
                        bg_hover_colors.push(this.options.scatter.colors.default.bg.hover);
                    }
                } else {
                    border_colors.push(this.options.scatter.colors.default.border.normal);
                    border_hover_colors.push(this.options.scatter.colors.default.border.hover);
                    bg_colors.push(this.options.scatter.colors.default.bg.normal);
                    bg_hover_colors.push(this.options.scatter.colors.default.bg.hover);
                }
                data.push({
                    x: parseFloat(table_data[i][x_field]),
                    y: parseFloat(table_data[i][y_field])
                });
                point_radius.push(this.options.scatter.point.radius.normal);
                point_hover_radius.push(this.options.scatter.point.radius.hover);
            }

            var chart_ctx = document.getElementById(this.options.element.scatter.canvas.replace('#', ''));
            chart_ctx.width = $(this.options.element.scatter.div).innerWidth();
            chart_ctx.height = this.options.scatter.height;
            var chart_options = {
                maintainAspectRatio: true,
                responsive: true,
                animation: {
                    duration: this.options.animation.duration
                },
                title: {
                    display: false
                },
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: true,
                    mode: 'label',
                    titleFontSize: this.options.scatter.tooltips.titleFontSize,
                    bodyFontSize: this.options.scatter.tooltips.bodyFontSize,

                    callbacks: {
                        title: function (tooltipItem, data) {
                            return data.labels[tooltipItem[0].index];
                        },
                        label: function (tooltipItem, data) {
                            var value = data.datasets[0].data[tooltipItem.index];
                            return [x_label + ': ' + value['x'] + x_postfix,
                                y_label + ': ' + value['y'] + y_postfix
                            ];
                        }
                    }
                },
                scales: {
                    xAxes: [{
                        position: 'bottom',
                        type: 'linear',
                        ticks: {
                            reverse: x_scale_reverse
                        },
                        scaleLabel: {
                            display: true,
                            labelString: x_label,
                            fontColor: 'black'
                        }
                    }],
                    yAxes: [{
                        position: 'left',
                        type: 'linear',
                        ticks: {
                            reverse: y_scale_reverse
                        },
                        scaleLabel: {
                            display: true,
                            labelString: y_label,
                            fontColor: 'black'
                        }
                    }]
                },
                showLines: false,
                chartArea: {
                    backgroundColor: this.options.scatter.colors.background
                }
            };
            var chart_data = {
                labels: labels,
                datasets: [{
                    data: data,
                    borderWidth: this.options.scatter.point.borderWidth,
                    pointBackgroundColor: bg_colors,
                    pointBorderWidth: border_colors,
                    pointHoverBackgroundColor: bg_hover_colors,
                    pointHoverBorderColor: border_hover_colors,
                    pointRadius: point_radius,
                    pointHoverRadius: point_hover_radius
                }]
            };
            this.scatter_chart = new Chart(chart_ctx, {
                type: 'line',
                data: chart_data,
                options: chart_options
            });
        },

        initComparison: function () {
            var table_data = $(this.element).bootstrapTable('getData');
            if (table_data.length > 1 && this.comparison_init) {
                for (var i = 0; i < table_data.length; i++) {
                    // Go through the table data and push them to the dropdown menus
                    var field = table_data[i][this.options.table.id_field].trim();
                    var label = table_data[i][this.options.table.id_field].trim().replace('_', ' ');
                    // A row
                    var active = '';
                    var selected = '0';
                    if (field == this.options.comparison.a_row) {
                        active = ' class="active" ';
                        selected = '1';
                        $(this.options.element.comparison.selector_a_button).html(label + ' ' + this.options.icon.caret);
                    }
                    $(this.options.element.comparison.selector_a_menu).append(
                        '<li' + active + '><a data-row="' + field + '" data-selected="' + selected + '" href="#" onclick="return false;">' + label + '</a></li>'
                    );

                    // B row
                    var active = '';
                    var selected = '0';
                    if (field == this.options.comparison.b_row) {
                        active = ' class="active" ';
                        selected = '1';
                        $(this.options.element.comparison.selector_b_button).html(label + ' ' + this.options.icon.caret);
                    }
                    $(this.options.element.comparison.selector_b_menu).append(
                        '<li' + active + '><a data-row="' + field + '" data-selected="' + selected + '" href="#" onclick="return false;">' + label + '</a></li>'
                    );
                }
                this.comparison_init = false; // prevent reinitialization
            }
        },
        updateComparison: function () {
            var self = this;

            if (this.comparison_chart) {
                this.comparison_chart.destroy();
            }

            var chart_ctx = document.getElementById(this.options.element.comparison.canvas.replace('#', ''));
            chart_ctx.width = $(this.options.element.comparison.div).innerWidth();
            chart_ctx.height = this.options.comparison.height;
            var table_options = $(this.element).bootstrapTable('getOptions');
            var table_data = $(this.element).bootstrapTable('getData');

            var sort_name = table_options.sortName;
            var id_field = table_options.idField;

            var a_row = '';
            var a_label = '';
            var b_row = '';
            var b_label = '';
            var set_id = '';
            var set_label = '';

            $(this.options.element.comparison.selector_a_menu + ' li a').each(function () {
                if ($(this).data('selected') == 1) {
                    a_row = $(this).data('row');
                    a_label = $(this).text();
                    return false;
                }
            });
            $(this.options.element.comparison.selector_b_menu + ' li a').each(function () {
                if ($(this).data('selected') == 1) {
                    b_row = $(this).data('row');
                    b_label = $(this).text();
                    return false;
                }
            });
            $(this.options.element.comparison.selector_set_menu + ' li a').each(function () {
                if ($(this).data('selected') == 1) {
                    set_id = $(this).data('set');
                    set_label = $(this).text();
                    return false;
                }
            });
            var set = $.grep(this.options.comparison.sets, function (e) {
                return e.title == set_label;
            });
            set = set[0];

            var set_fields = [];
            for (var i = 0; i < set.fields.length; i++) {
                var field_name = set.fields[i];
                var chartable = $(this.element).find('thead tr th[data-field=\"' + field_name + '\"]').data('chartable');

                var title;
                if (set.hasOwnProperty('field_titles') && set.field_titles[i]) {
                    title = set.field_titles[i];
                } else {
                    title = $(this.element).find('thead tr th[data-field=\"' + field_name + '\"]').text().replace(/(\r\n|\n|\r)/gm, "").trim();
                }

                var value_postfix = $(this.element).find('thead tr th[data-field=\"' + field_name + '\"]').data('postfix');
                set_fields.push({
                    'field': field_name,
                    'title': title,
                    'chartable': chartable,
                    'value_postfix': value_postfix
                });
            }

            var data_a = $.grep(table_data, function (e) {
                var id = e[self.options.comparison.row_id_field].replace(/(\r\n|\n|\r)/gm, "").trim();
                return id == a_row;
            });
            if (data_a.length == 1) {
                data_a = data_a[0];
            }
            var data_b = $.grep(table_data, function (e) {
                var id = e[self.options.comparison.row_id_field].replace(/(\r\n|\n|\r)/gm, "").trim();
                return id == b_row;
            });
            if (data_b.length == 1) {
                data_b = data_b[0];
            }
            var labels = [];
            var set_a_data = [];
            var set_b_data = [];
            for (var field_id = 0; field_id < set_fields.length; field_id++) {
                if (set_fields[field_id].chartable) {
                    labels.push(set_fields[field_id].title);
                    set_a_data.push(parseFloat(data_a[set_fields[field_id].field]));
                    set_b_data.push(parseFloat(data_b[set_fields[field_id].field]));
                }
            }
            var datasets = [
                {
                    label: a_label,
                    data: set_a_data,
                    backgroundColor: this.options.comparison.a_color
                },
                {
                    label: b_label,
                    data: set_b_data,
                    backgroundColor: this.options.comparison.b_color
                }
            ];

            if (set_a_data.length > 0 && set_b_data.length > 0) {
                $(this.options.element.comparison.div).show(0);
                var chart_data = {
                    labels: labels,
                    datasets: datasets
                };
                var chart_options = {
                    maintainAspectRatio: true,
                    responsive: true,
                    animation: {
                        duration: this.options.animation.duration
                    },
                    title: {
                        display: false
                    },
                    legend: {
                        display: false,
                        position: 'bottom'
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            stacked: false,
                            ticks: {
                                autoSkip: false,
                                maxRotation: 90
                            }
                        }],

                        yAxes: [{
                            stacked: false,
                            type: 'linear',
                            ticks: {
                                beginAtZero: true
                            },
                            scaleLabel: {
                                display: true,
                                labelString: set.data_axis_title,
                                fontColor: 'black'
                            }
                        }]
                    },

                    tooltips: {
                        enabled: true,
                        mode: 'label',
                        titleFontSize: this.options.comparison.tooltips.titleFontSize,
                        bodyFontSize: this.options.comparison.tooltips.bodyFontSize,
                        callbacks: {
                            title: function (tooltipItem, data) {
                                return data.labels[tooltipItem[0].index];
                            }
                        }
                    },
                    chartArea: {
                        backgroundColor: this.options.comparison.colors.background
                    }
                };
                this.comparison_chart = new Chart(chart_ctx, {
                    type: this.options.comparison.type,
                    data: chart_data,
                    options: chart_options
                });
            } else {
                if ($(this.options.element.comparison.div).is(":visible")) {
                    $(this.options.element.comparison.div).hide(this.options.animation.hide_speed);
                }
            }
        },

        showBarChart: function () {
            var self = this;
            if (this.validBar()) {
                if ($(this.options.element.bar.div).is(":hidden")) {
                    $(this.options.element.bar.div).show(this.options.animation.show_speed, function () {
                        self.updateBar();
                    });
                }
            }
        },
        hideBarChart: function () {
            var self = this;
            if ($(this.options.element.bar.div).is(":visible")) {
                $(this.options.element.bar.div).hide(this.options.animation.hide_speed, function () {
                });
            }
        },

        showScatterChart: function () {
            var self = this;
            if ($(this.options.element.scatter.div).is(":hidden")) {
                $(this.options.element.scatter.div).show(this.options.animation.show_speed, function () {
                    self.updateScatter();
                });
            }
        },
        hideScatterChart: function () {
            var self = this;
            if ($(this.options.element.scatter.div).is(":visible")) {
                $(this.options.element.scatter.div).hide(this.options.animation.hide_speed, function () {
                });
            }
        },

        showComparisonChart: function () {
            var self = this;
            if ($(this.options.element.comparison.div).is(":hidden")) {
                $(this.options.element.comparison.div).show(this.options.animation.show_speed, function () {
                    self.updateComparison();
                });
            }
        },
        hideComparisonChart: function () {
            var self = this;
            if ($(this.options.element.comparison.div).is(":visible")) {
                $(this.options.element.comparison.div).hide(this.options.animation.hide_speed, function () {
                });
            }
        },

        updateTags: function () {
            var self = this;
            if (this.options.tag_mode == 'global') {
                var map = {};
                var values = {};
                $.each(this.field_meta, function (index, meta) {
                    if (meta.tag) {
                        $(self.element).find('tbody tr').each(function () {
                            var value = $(this).find('td').eq(index).text().trim();
                            value.split(',').forEach(function (item) {
                                if (!(item in values)) {
                                    values[item] = 0;
                                }
                                values[item]++;
                            });
                        });
                    }
                });
                var sortable = [];
                for (var value_group in values) {
                    sortable.push([value_group, values[value_group]]);
                    sortable.sort(
                        function (a, b) {
                            return b[1] - a[1]
                        }
                    )
                }
                $.each(sortable, function (i, val) {
                    map[val[0]] = i;
                });

                $.each(this.field_meta, function (index, meta) {
                    if (meta.tag) {
                        $(self.element).find('tbody tr').each(function () {
                            var value = $(this).find('td').eq(index).text().trim();
                            var html = '';
                            value.split(',').forEach(function (item) {
                                var tag_css = self.options.tags.css[map[item]];
                                html += '<span class="' + tag_css + '">' + item + '</span>';
                            });
                            $(this).find('td').eq(index).html(html);
                        })
                    }
                })
            } else if (this.options.tag_mode == 'column') {
                var map = {};
                $.each(this.field_meta, function (index, meta) {
                    if (meta.tag) {
                        var values = {};
                        $(self.element).find('tbody tr').each(function () {
                            var value = $(this).find('td').eq(index).text().trim();
                            value.split(',').forEach(function (item) {
                                if (!(item in values)) {
                                    values[item] = 0;
                                }
                                values[item]++;
                            });
                        });
                        var sortable = [];
                        for (var value_group in values) {
                            sortable.push([value_group, values[value_group]]);
                            sortable.sort(
                                function (a, b) {
                                    return b[1] - a[1]
                                }
                            )
                        }
                        $.each(sortable, function (i, val) {
                            map[val[0]] = i;
                        });
                        $(self.element).find('tbody tr').each(function () {
                            var value = $(this).find('td').eq(index).text().trim();
                            var html = '';
                            value.split(',').forEach(function (item) {
                                var tag_css = self.options.tags.css[map[item]];
                                html += '<span class="' + tag_css + '">' + item + '</span>';

                            });
                            $(this).find('td').eq(index).html(html);
                        })
                    }
                })
            }

        },
        updateRank: function () {
            // Calculate rank values for whole table while taking account rows with same value
            this.table_rank = [];
            var table_data = $(this.element).bootstrapTable('getData');
            if (table_data.length > 1) {
                var table_options = $(this.element).bootstrapTable('getOptions');
                var sort_name = table_options.sortName;
                var rank = 0;
                var prev = table_data[0][sort_name] - 1;
                var rank_unique = 1;
                for (var i = 0; i < table_data.length; i++) {
                    var value = parseFloat(table_data[i][sort_name]);
                    if (value != prev) {
                        rank++;
                        prev = value;
                        this.table_rank.push(rank);
                    } else {
                        rank_unique = 0;
                        if (this.options.rank_mode == 'grouped_empty') {
                            this.table_rank.push('');
                        } else if (this.options.rank_mode == 'grouped_muted') {
                            this.table_rank.push('<span class=\"text-muted\">' + rank + '</span>')
                        } else {
                            this.table_rank.push(rank);
                        }
                    }
                }
            }
            $(this.element).bootstrapTable('updateCell', {index: 0, field: 'rank', value: '-'});
        },
        getRank: function (index) {
            // Get precomputed rank value
            return this.table_rank[index];
        },
        log: function (string) {
            // Console printing for debugging purposes
            if (this.options.debug) {
                console.log(string);
            }
        }
    };

    // Public functions
    function getRank(index) {
        if (typeof $(this).data(pluginName) !== 'undefined') {
            return $(this).data(pluginName).getRank(index);
        } else {
            return '';
        }
    }

    function updateChart() {
        $(this).data(pluginName).updateChart();
    }

    $.fn[pluginName] = function (options) {
        var element = $(this);
        var name = pluginName;

        if (options === undefined || typeof options === 'object') {
            // create plugin
            return this.map(function () {
                var $t = $(this);
                var object = $t.data(name);

                if (undefined == object) {
                    // Stores the new `Plugin` object in the calling element's jQuery `data` method
                    var plugin = new Plugin(element, $.extend(true, {}, defaults, options), name);
                    $t.data(name, plugin);
                    //plugin.refreshTable();
                }
            });
        } else if (typeof options === 'string') {
            // Execute string command on plugin
            //var property = arguments[1];
            var args = Array.prototype.slice.call(arguments);
            args.splice(0, 1);
            return commands[arguments[0]].apply(element, args);
        }
    }

}(jQuery, window, document));

// ========================================
// Helper functions for bootstrap-table.js
// ========================================
function valueSorter(a, b) {
    // Value sorter which makes sure empty cells are last always.
    if (a.trim()) {
        var a_float = parseFloat(a.replace('%', ''));
        if (!isNaN(a_float)) {
            a = a_float;
        }
    } else {
        a = -Infinity;
    }
    if (b.trim()) {
        var b_float = parseFloat(b.replace('%', ''));
        if (!isNaN(b_float)) {
            b = b_float;
        }
    } else {
        b = -Infinity;
    }
    if (a < b) {
        return -1;
    }
    if (a > b) {
        return 1;
    }
    return 0;
}

function valueSorterReverse(a, b) {
    // Value sorter which makes sure empty cells are last always in case of reversed sort order.
    if (a.trim()) {
        var a_float = parseFloat(a.replace('%', ''));
        if (!isNaN(a_float)) {
            a = a_float;
        }
    } else {
        a = Infinity;
    }
    if (b.trim()) {
        var b_float = parseFloat(b.replace('%', ''));
        if (!isNaN(b_float)) {
            b = b_float;
        }
    } else {
        b = Infinity;
    }
    if (a < b) {
        return -1;
    }
    if (a > b) {
        return 1;
    }
    return 0;
}

function percentageFormatter(value, row, index) {
    if ($.isNumeric(value)) {
        return value + ' %';
    } else {
        return value;
    }
}

function valueFormatter_int(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(0);
    } else {
        return value;
    }
}

function valueFormatter_int(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(0);
    } else {
        return value;
    }
}

function valueFormatter_float1(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(1);
    } else {
        return value;
    }
}

function valueFormatter_float2(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(2);
    } else {
        return value;
    }
}

function valueFormatter_float3(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(3);
    } else {
        return value;
    }
}

function valueFormatter_float4(value, row, index) {
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(4);
    } else {
        return value;
    }
}

function valueFormatter_int_percentage(value, row, index) {
    // Cell formatter for percentage values
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(0) + ' %';
    } else {
        return value;
    }
}

function valueFormatter_float1_percentage(value, row, index) {
    // Cell formatter for percentage values
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(1) + ' %';
    } else {
        return value;
    }
}

function valueFormatter_float2_percentage(value, row, index) {
    // Cell formatter for percentage values
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(2) + ' %';
    } else {
        return value;
    }
}

function valueFormatter_float3_percentage(value, row, index) {
    // Cell formatter for percentage values
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(3) + ' %';
    } else {
        return value;
    }
}

function valueFormatter_float4_percentage(value, row, index) {
    // Cell formatter for percentage values
    if ($.isNumeric(value)) {
        return parseFloat(value).toFixed(4) + ' %';
    } else {
        return value;
    }
}

function valueFormatter_list(value, row, index) {
    var items = value.split(',');
    if (items.length > 0) {
        return items.join('<br>');
    } else {
        return value;
    }
}

function runningFormatter(value, row, index) {
    // Cell formatter for normal ranking
    return (index + 1);
}
