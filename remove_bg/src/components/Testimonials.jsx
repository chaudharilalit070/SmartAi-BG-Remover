import { testimonials } from "../assets/assets";

const Testimonials = () => {
  return (
    <div className="max-w-7xl px-4 mx-auto sm:px-6 lg:px-8 py-12">
      {/* Title Section */}
      <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-12 text-center">
        They love us. You will too.
      </h2>

      {/* Body Section */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
        {testimonials.map((testimonial) => (
          <div
            key={testimonial.id}
            className="flex flex-col justify-between max-w-md mx-auto md:mx-0 bg-white rounded-2xl shadow-md hover:shadow-xl transition-shadow duration-300 border border-gray-100"
          >
            <div className="flex flex-col px-6 pt-8 pb-6 space-y-5">
              {/* Online quote image via URI */}
              <img
                src="https://cdn-icons-png.flaticon.com/512/633/633759.png"
                alt="Quote icon"
                className="w-10 h-10 text-indigo-500"
              />

              <p className="text-gray-700 text-base leading-relaxed italic" style={{ hyphens: "auto" }}>
                “{testimonial.quote}”
              </p>
            </div>

            <div className="flex space-x-3 items-center bg-gray-50 px-6 py-5 rounded-b-2xl border-t border-gray-100">
              <div className="flex flex-col justify-center">
                <p className="font-semibold text-gray-900">{testimonial.author}</p>
                <p className="text-gray-500 text-sm mt-1">{testimonial.handle}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Testimonials;
