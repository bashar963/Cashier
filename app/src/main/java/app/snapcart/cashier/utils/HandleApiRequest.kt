package app.snapcart.cashier.utils

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApiRequest(
    request: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = request.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        } else {
            // TODO can modify to get error from body response

            ApiError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiException(e)
    }
}
