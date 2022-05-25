graph
[
	directed	1
	node
	[
		id	1
		template	""
		label	"S3"
		graphics
		[
			x	0.0
			y	0.0
			w	40.0
			h	40.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	2
		template	""
		label	"S12"
		graphics
		[
			x	0.0
			y	180.0
			w	40.0
			h	40.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	3
		template	""
		label	"S19,18,9,11,5,2"
		graphics
		[
			x	0.0
			y	900.0
			w	80.0
			h	80.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	4
		template	""
		label	"S4,0"
		graphics
		[
			x	0.0
			y	1080.0
			w	80.0
			h	80.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	5
		template	""
		label	"S10,14"
		graphics
		[
			x	110.0
			y	120.0
			w	80.0
			h	80.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	6
		template	""
		label	"S13,15,16,8"
		graphics
		[
			x	110.0
			y	420.0
			w	80.0
			h	80.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	7
		template	""
		label	"S17"
		graphics
		[
			x	110.0
			y	600.0
			w	40.0
			h	40.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	8
		template	""
		label	"S7,6"
		graphics
		[
			x	220.0
			y	540.0
			w	80.0
			h	80.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	node
	[
		id	9
		template	""
		label	"S1"
		graphics
		[
			x	330.0
			y	1020.0
			w	40.0
			h	40.0
			fill	"#808080"
			outline	"#000000"
			pattern	"Solid"
			stipple	"Solid"
			lineWidth	1.0
			type	"circle"
		]
	]
	edge
	[
		source	1
		target	9
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 0.0 ]
				point [ x 55.0 y 1014.0 ]
				point [ x 330.0 y 1020.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	1
		target	5
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 0.0 ]
				point [ x 55.0 y 114.0 ]
				point [ x 110.0 y 120.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	1
		target	8
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 0.0 ]
				point [ x 55.0 y 534.0 ]
				point [ x 220.0 y 540.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	2
		target	9
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 180.0 ]
				point [ x 55.0 y 1014.0 ]
				point [ x 330.0 y 1020.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	2
		target	6
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 180.0 ]
				point [ x 55.0 y 414.0 ]
				point [ x 110.0 y 420.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	3
		target	9
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 900.0 ]
				point [ x 55.0 y 1014.0 ]
				point [ x 330.0 y 1020.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	5
		target	3
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 110.0 y 120.0 ]
				point [ x 55.0 y 894.0 ]
				point [ x 0.0 y 900.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	5
		target	8
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 110.0 y 120.0 ]
				point [ x 165.0 y 534.0 ]
				point [ x 220.0 y 540.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	6
		target	8
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 110.0 y 420.0 ]
				point [ x 165.0 y 534.0 ]
				point [ x 220.0 y 540.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	6
		target	4
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 110.0 y 420.0 ]
				point [ x 55.0 y 1074.0 ]
				point [ x 0.0 y 1080.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	7
		target	3
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 110.0 y 600.0 ]
				point [ x 55.0 y 894.0 ]
				point [ x 0.0 y 900.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	8
		target	3
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 220.0 y 540.0 ]
				point [ x 165.0 y 894.0 ]
				point [ x 0.0 y 900.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	9
		target	4
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 330.0 y 1020.0 ]
				point [ x 275.0 y 1074.0 ]
				point [ x 0.0 y 1080.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	1
		target	2
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			fill	"#000000"
		]
	]
	edge
	[
		source	2
		target	3
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			fill	"#000000"
		]
	]
	edge
	[
		source	3
		target	4
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			fill	"#000000"
		]
	]
	edge
	[
		source	5
		target	6
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			fill	"#000000"
		]
	]
	edge
	[
		source	6
		target	7
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			fill	"#000000"
		]
	]
	edge
	[
		source	1
		target	4
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 0.0 ]
				point [ x -61.0 y 56.0 ]
				point [ x -61.0 y 1024.0 ]
				point [ x 0.0 y 1080.0 ]
			]
			fill	"#000000"
		]
	]
	edge
	[
		source	1
		target	3
		graphics
		[
			type	"line"
			type	"line"
			arrow	"last"
			stipple	"Solid"
			lineWidth	1.0000000000
			Line [
				point [ x 0.0 y 0.0 ]
				point [ x -36.0 y 31.0 ]
				point [ x -36.0 y 869.0 ]
				point [ x 0.0 y 900.0 ]
			]
			fill	"#000000"
		]
	]
]
