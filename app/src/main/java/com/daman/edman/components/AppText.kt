package com.daman.edman.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.text.ParagraphIntrinsics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daman.edman.R
import com.daman.edman.ui.theme.AppBorderColor
import com.daman.edman.ui.theme.ArabotoFont
import com.daman.edman.ui.theme.grayColor

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 24,
    fontWeight: FontWeight = FontWeight.Medium,
    color: Color = Color.White
) {

    Text(
        text = text,
        style = TextStyle(
            fontFamily = ArabotoFont,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            color = color,
            lineHeight = fontSize.sp,
            letterSpacing = 0.5.sp
        ),
        modifier = modifier
    )
}

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = grayColor,
    textAlign: TextAlign = TextAlign.Center
) {

    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontFamily = ArabotoFont,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            color = color,
            lineHeight = fontSize.sp,
            letterSpacing = 0.5.sp
        ),
        textAlign = textAlign,
    )
}

@Composable
fun NormalTextWithBorder(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = grayColor
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, AppBorderColor), shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        NormalText(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight
        )
    }

}


@Preview
@Composable
fun PreviewheaderText() {
    Column {
        HeaderText(text = "اختر اللغة")
        NormalText(text = "hojv gym")
    }

}

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Center,
    lineHeight: TextUnit = TextUnit.Unspecified,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    BoxWithConstraints(modifier = modifier) {
        var shrunkFontSize = fontSize
        val calculateIntrinsics = @Composable {
            ParagraphIntrinsics(
                text, TextStyle(
                    color = color,
                    fontSize = shrunkFontSize,
                    fontWeight = fontWeight,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    fontFamily = fontFamily,
                    textDecoration = textDecoration,
                    fontStyle = fontStyle,
                    letterSpacing = letterSpacing
                ),
                density = LocalDensity.current,
                resourceLoader = LocalFontLoader.current
            )
        }

        var intrinsics = calculateIntrinsics()
        with(LocalDensity.current) {
            while (intrinsics.maxIntrinsicWidth > maxWidth.toPx()) {
                shrunkFontSize *= 0.9
                intrinsics = calculateIntrinsics()
            }
        }
        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = shrunkFontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            onTextLayout = onTextLayout,
            style = style
        )
    }
}