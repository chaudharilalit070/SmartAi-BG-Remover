import axios from "axios";
import { useContext } from "react";
import toast from "react-hot-toast";
import { AppContext } from "../context/AppContext";

const {backendUrl} =useContext(AppContext);
export const placeOrder = async ({ PlanId, getToken, onSuccess, backendUrl }) => {
  try {
    const token = await getToken();
    

    const response = await axios.post(
      `${backendUrl}/orders?planId=${PlanId}`, // ✅ Fix: planId, not planeId
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (response.status === 200) {
      // const orderData = response.data?.data; // ✅ Fix: access .data from custom response structure
      initializePayment({ order: response.data.data, getToken, onSuccess, backendUrl });
    }
  } catch (error) {
    toast.error(error.message);
  }
};
 
const initializePayment = ({ order, getToken, onSuccess, backendUrl }) => {
  const options = {
    key: import.meta.env.VITE_RAZORPAY_KEY_ID,
    amount: order.amount,
    currency: order.currency,
    name: "Credit payment",
    description: "Credit payment", // ✅ Fix: spelling of description
    order_id: order.id, // ✅ Fix: use order.id (from RazorpayOrderDTO)
    receipt: order.receipt,
    handler: async (paymentDetails) => {
      try {
        const token = await getToken();

        const response = await axios.post(
          `${backendUrl}/orders/verify`,
          paymentDetails,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (response.status === 200) {
          toast.success("Credits Added");
          onSuccess?.();
        }
      } catch (error) {
        toast.error(error.message);
      }
    },
  };

  const rzp = new window.Razorpay(options);
  rzp.open();
};
