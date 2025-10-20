package tw.edu.pu.csim.tcyang.basicui

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {

    // 圖片資源清單
    var Animals = listOf(R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9)

    var AnimalsName = arrayListOf("鴨子","企鵝",
        "青蛙","貓頭鷹","海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    var flag by remember { mutableStateOf("test") }

    // **圖片按鈕狀態：追蹤目前顯示哪張圖片 (0 或 1)**
    var currentImageIndex by remember { mutableStateOf(0) }


    // 取得當前的 Context
    val context = LocalContext.current

    // 使用 remember 儲存 MediaPlayer 實例
    var mper: MediaPlayer? by remember { mutableStateOf(null) }

    // 使用 DisposableEffect 來管理 MediaPlayer 的生命週期
    DisposableEffect(Unit) {
        onDispose {
            mper?.release()
            mper = null
        }
    }


    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // 保持垂直靠上
    ) {

        // 標題區域
        Text(text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))

        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(text = stringResource(R.string.app_author),
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row {
            // Android, Compose, Firebase 圖示
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )

        }

        Spacer(modifier = Modifier.size(10.dp))

        // LazyRow 顯示動物
        LazyRow {
            items(51) { index ->
                Text(text = "$index:")
                Text(text = AnimalsName[index % 10])

                Image(
                    painter = painterResource(id = Animals[index % 10]),
                    contentDescription = "可愛動物",
                    modifier = Modifier.size(60.dp)
                )

            }

        }

        Spacer(modifier = Modifier.size(10.dp))


        // ----------------------------------------------------------------------------------
        // 原本的 "按鈕測試" 按鈕
        // ----------------------------------------------------------------------------------
        Button(
            onClick = {
                if (flag == "test"){
                    flag = "abc"
                }
                else{
                    flag = "test"
                }

                Toast.makeText(
                    context,
                    "Compose 按鈕被點擊了！",
                    Toast.LENGTH_SHORT
                ).show()

            }

        ) {
            Text("按鈕測試")
        }

        Text(text = flag)

        Spacer(modifier = Modifier.size(10.dp))

        // ----------------------------------------------------------------------------------
        // 原本的【三個並排按鈕】
        // ----------------------------------------------------------------------------------
        Row{
            Button(
                onClick = {
                    mper?.release()
                    mper = null

                    mper = MediaPlayer.create(context, R.raw.tcyang)
                    mper?.start()
                },

                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .fillMaxHeight(0.8f),

                colors = ButtonDefaults.buttonColors(Color.Green)


            ) {
                Text(text = "歡迎", color = Color.Blue)
                Text(text = "修課", color = Color.Red)
                Image(painterResource(id = R.drawable.teacher),
                    contentDescription ="teacher icon")

            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(onClick = {
                mper?.release()
                mper = null

                mper = MediaPlayer.create(context, R.raw.fly)
                mper?.start()
            },

                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.4f),

                colors = ButtonDefaults.buttonColors(Color.Blue)

            ) {
                Text(text = "展翅飛翔", color = Color.White)
                Image(
                    painterResource(id = R.drawable.fly),
                    contentDescription ="fly icon")

            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(onClick = {
                val activity = context as? Activity
                activity?.finish()},

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),

                shape = CutCornerShape(10),

                border = BorderStroke(1.dp, Color.Blue),

                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)

            ) {
                Text(text = "結束App")
            }
        }

        // ----------------------------------------------------------------------------------
        // ✨ 新增的圖片切換按鈕，現在位於最底部的 Row 按鈕之下
        // ----------------------------------------------------------------------------------

        Spacer(modifier = Modifier.size(20.dp)) // 增加間隔

        // 圖片判斷邏輯 (切換 animal0 和 animal1)
        val currentAnimalResId = if (currentImageIndex == 0) {
            Animals[0] // 鴨子 (animal0)
        } else {
            Animals[1] // 企鵝 (animal1)
        }

        // 圖形按鈕：點擊切換圖片
        Button(
            onClick = {
                // 1. 切換狀態：0 變 1，1 變 0
                currentImageIndex = if (currentImageIndex == 0) 1 else 0

                // 2. 顯示 Toast 訊息
                val name = AnimalsName[currentImageIndex]
                Toast.makeText(
                    context,
                    "已切換到 $name",
                    Toast.LENGTH_SHORT
                ).show()
            },
            // 設定按鈕樣式
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            elevation = ButtonDefaults.buttonElevation(0.dp),
            border = BorderStroke(2.dp, Color.Red),
            modifier = Modifier.size(120.dp)
        ) {
            // 按鈕的內容：顯示動態切換的動物圖片
            Image(
                painter = painterResource(id = currentAnimalResId),
                contentDescription = "切換動物圖示",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        // ----------------------------------------------------------------------------------

    }
}