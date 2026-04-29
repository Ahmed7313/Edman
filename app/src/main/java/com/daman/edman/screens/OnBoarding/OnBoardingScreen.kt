package com.daman.edman.screens.OnBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aramex.mypos.Presentation.NavGrapghs.LoginScreen
import com.daman.edman.R
import com.daman.edman.screens.components.AppHolder
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.trend.thecontent.screens.components.MainButton
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.data.local.preference.SavePreferences
import kotlinx.coroutines.launch

data class OnBoardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current
    val preferences = SavePreferences(context)
    val coroutineScope = rememberCoroutineScope()

    val pages = listOf(
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("logo_1_1", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "أهلاً بيك في اضمن",
            description = "اضمن الوسيط الأمن في التعاملات المالية لتجنب الإحتيال ويمنع تحويل دفع آمنة"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_1", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "خطوات بسيطة وواضحة لضمان حقك",
            description = "متفق على الصفقة سدد، ندفع الفلوس تنحجز جوه اضمن و استلمت يتم بكل أمان"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_2", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "لو إنت مشتري",
            description = "المشتري يدفع عن طريق اضمن"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_3", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "لو إنت بائع",
            description = "البائع يسجل تفاصيل المنتج أو الخدمة"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_4", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "لو إنت بائع",
            description = "البائع يشحن ويسلم المبيع أو الخدمة"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_5", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "لو إنت مشتري",
            description = "المشتري يوافق على الاستلام"
        ),
        OnBoardingPage(
            imageRes = context.resources.getIdentifier("ic_intro_6", "drawable", context.packageName).takeIf { it != 0 } ?: R.drawable.logo_1_1,
            title = "اضمن",
            description = "اضمن يحول الفلوس للبائع بعد تأكيد المشتري للاستلام"
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    val onFinish = {
        preferences.putIntroPassed(true)
        navController.navigate(LoginScreen) {
            popUpTo(0) { inclusive = true }
        }
    }

    AppHolder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing)
        ) {
            // Skip Button
            Text(
                text = "تخطي",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { onFinish() }
                    .padding(vertical = 16.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { position ->
                val page = pages[position]
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = page.imageRes),
                        contentDescription = "Onboarding Image",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    HeaderText(
                        text = page.title,
                        textAlign = TextAlign.Center,
                        fontSize = 24,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NormalText(
                        text = page.description,
                        textAlign = TextAlign.Center,
                        fontSize = 14,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // Next / Start Button
            MainButton(
                text = if (pagerState.currentPage == pages.size - 1) "ابدأ الان" else "التالي",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                onClick = {
                    if (pagerState.currentPage == pages.size - 1) {
                        onFinish()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )
        }
    }
}
