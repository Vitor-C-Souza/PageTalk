package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.theme.PageTalkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCustom(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Buscar PDFs..."
) {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = placeholder,
                color = colorScheme.outline,
                fontSize = 15.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange("") },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                        .background(colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpar",
                        tint = colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorScheme.surfaceVariant.copy(alpha = 0.5f),
            unfocusedContainerColor = colorScheme.surfaceVariant.copy(alpha = 0.3f),
            disabledContainerColor = colorScheme.surfaceVariant,
            cursorColor = colorScheme.primary,
            focusedBorderColor = colorScheme.primary.copy(alpha = 0.5f),
            unfocusedBorderColor = colorScheme.primary.copy(alpha = 0.2f),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    onPrimary: Color = MaterialTheme.colorScheme.tertiary // B8A9FF mapping
) {
    Surface(
        modifier = modifier,
        color = color.copy(alpha = 0.2f),
        shape = CircleShape,
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = color.copy(alpha = 0.3f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = onPrimary,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun InputsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        val colorScheme = MaterialTheme.colorScheme
        Surface(color = colorScheme.background) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Search Input",
                    color = colorScheme.tertiary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                InputCustom(
                    value = "Design Patterns",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Chips / Tags",
                    color = colorScheme.tertiary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Chip(
                        text = "Em progresso",
                        color = colorScheme.primary,
                        onPrimary = colorScheme.tertiary
                    )
                    Chip(
                        text = "Concluído",
                        color = Color(0xFF4ADE80),
                        onPrimary = Color(0xFF4ADE80)
                    )
                    Chip(
                        text = "Novo",
                        color = Color(0xFFFACC15),
                        onPrimary = Color(0xFFFACC15)
                    )
                }
            }
        }
    }
}
