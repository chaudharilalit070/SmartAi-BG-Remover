import { useAuth, useClerk } from "@clerk/clerk-react";
import { plans } from "../assets/assets";
import { placeOrder } from "../service/OrderService";
import { AppContext } from '../context/AppContext';
import { useContext } from "react";

const BuyCredits = () => {
  const { isSignedIn, getToken } = useAuth();
  const { openSignIn } = useClerk();
  const { loadUserCrdits, backendUrl } = useContext(AppContext);

  const handleOrder = (planId) => {
    if (!isSignedIn) {
      return openSignIn();
    }
    placeOrder({
      planId,
      getToken,
      onSuccess: () => {
        loadUserCrdits();
      },
      backendUrl
    });
  };

  return (
    <section className="py-24 px-4 md:px-16 lg:px-32 bg-white text-gray-900 font-sans">
      <div className="max-w-7xl mx-auto">
        {/* Header */}
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-serif font-bold tracking-tight leading-tight">
            Choose Your Perfect Package
          </h2>
          <p className="mt-6 max-w-2xl mx-auto text-gray-600 text-lg leading-relaxed">
            Discover thoughtfully crafted packages tailored for every creator, brand, or storyteller — 
            whether you're starting small or scaling big.
          </p>
        </div>

        {/* Cards */}
        <div className="grid gap-12 sm:grid-cols-2 lg:grid-cols-3">
          {plans.map((item) => (
            <div
              key={item.id}
              className={`relative flex flex-col justify-between p-8 rounded-3xl shadow-xl transition-all duration-300 border ${
                item.popular
                  ? "bg-gradient-to-br from-gray-900 to-gray-800 border-purple-600 text-white"
                  : "bg-white border-gray-200 text-gray-800"
              } hover:-translate-y-2 hover:shadow-2xl`}
            >
              {/* Badge */}
              {item.popular && (
                <div className="absolute -top-4 left-1/2 -translate-x-1/2 bg-purple-600 text-white px-4 py-1 rounded-full text-xs font-semibold tracking-wider uppercase shadow-md">
                  Most Popular
                </div>
              )}

              {/* Content */}
              <div className="text-center mb-8">
                <h3 className="text-2xl font-bold mb-3">{item.name}</h3>
                <div className="text-4xl font-extrabold text-violet-500">
                  ₹{item.price}
                </div>
              </div>

              <ul className="space-y-4 text-sm mb-10">
                <li className="flex items-center gap-2">
                  ✅ <span>{item.credits}</span>
                </li>
                <li className="flex items-center gap-2">
                  📝 <span>{item.description}</span>
                </li>
              </ul>

              {/* CTA */}
              <button
                className={`w-full py-3 rounded-full font-semibold text-sm transition-all duration-300 ${
                  item.popular
                    ? "bg-white text-gray-900 hover:bg-gray-100"
                    : "bg-gradient-to-r from-purple-600 to-indigo-600 text-white hover:from-purple-700 hover:to-indigo-700"
                }`}
                onClick={() => handleOrder(item.id)} // ✅ FIXED
              >
                Choose Plan
              </button>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default BuyCredits;
