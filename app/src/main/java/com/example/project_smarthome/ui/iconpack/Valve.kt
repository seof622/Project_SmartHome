package com.example.project_smarthome.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.project_smarthome.ui.IconPack

public val IconPack.Valve: ImageVector
    get() {
        if (_valve != null) {
            return _valve!!
        }
        _valve = Builder(name = "Valve", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(440.0f, 320.0f)
                verticalLineToRelative(-120.0f)
                lineTo(280.0f, 200.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(400.0f)
                verticalLineToRelative(80.0f)
                lineTo(520.0f, 200.0f)
                verticalLineToRelative(120.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(160.0f, 840.0f)
                verticalLineToRelative(-320.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(120.0f)
                verticalLineToRelative(-120.0f)
                horizontalLineToRelative(-40.0f)
                verticalLineToRelative(-80.0f)
                horizontalLineToRelative(320.0f)
                verticalLineToRelative(80.0f)
                horizontalLineToRelative(-40.0f)
                verticalLineToRelative(120.0f)
                horizontalLineToRelative(120.0f)
                verticalLineToRelative(-40.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(320.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(-40.0f)
                lineTo(240.0f, 800.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(240.0f, 720.0f)
                horizontalLineToRelative(480.0f)
                verticalLineToRelative(-80.0f)
                lineTo(520.0f, 640.0f)
                verticalLineToRelative(-200.0f)
                horizontalLineToRelative(-80.0f)
                verticalLineToRelative(200.0f)
                lineTo(240.0f, 640.0f)
                verticalLineToRelative(80.0f)
                close()
                moveTo(480.0f, 720.0f)
                close()
            }
        }
        .build()
        return _valve!!
    }

private var _valve: ImageVector? = null
