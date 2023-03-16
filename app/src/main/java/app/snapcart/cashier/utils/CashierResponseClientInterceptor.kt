package app.snapcart.cashier.utils

import android.util.Log
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
import io.grpc.MethodDescriptor

class CashierResponseClientInterceptor : ClientInterceptor {

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>?,
        callOptions: CallOptions?,
        next: Channel?
    ): ClientCall<ReqT, RespT> {
        return object : SimpleForwardingClientCall<ReqT, RespT>(
            next?.newCall(method, callOptions)
        ) {
            override fun start(responseListener: Listener<RespT>?, headers: io.grpc.Metadata?) {
                super.start(
                    object : SimpleForwardingClientCallListener<RespT>(
                        responseListener
                    ) {
                        override fun onMessage(message: RespT) {
                            Log.d("CashierResponseClientInterceptor", "Received response from Server: $message")
                            super.onMessage(message)
                        }
                    },
                    headers
                )
            }
        }
    }
}
