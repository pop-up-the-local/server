package com.popup_the_local.server.common.responsebody

import org.springframework.http.HttpStatus

enum class SuccessCode(
    val statusCode: Int,
    val msg: String,
) {
    SUCCESS(HttpStatus.OK.value(), "정상 처리 되었습니다."),
}

enum class ErrorCode(
    val statusCode: Int,
    val msg: String,
) {
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "요청 값이 올바르지 않습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "요청 값을 찾을 수 없습니다."),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY.value(), "서버가 요청을 처리하는데 실패했습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "token에 오류가 발생했습니다."),
}
