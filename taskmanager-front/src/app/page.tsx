"use client";

import { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";

export default function Home() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [titulo, setTitulo] = useState("");
  const [description, setDescription] = useState("");


  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setTitulo("");
  };



  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!titulo.trim()) {
      alert("O titulo é obrigatorio!")
      return;
    }

    const data = {
      title: titulo,
      description: description,
    };

    try {
      const response = await fetch ("http://localhost:8080/api/tasks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const result = await response.json();
        closeModal();
      } else {
        console.error("Erro ao criar tarefa: ", response.statusText);
      }
    } catch (error) {
      console.error("Erro na requisição: ", error)
    }
  }

  return (
    <div className="min-h-screen bg-white dark:bg-black p-4 sm:p-6 lg:p-8 flex">
      <div className="max-w-4xl mx-auto">
        <header className="flex-col text-center justify-center">
          <h1 className="text-4xl my-6 font-bold text-black dark:text-white tracking-tight">
            TaskCoder
          </h1>

          <div className="max-w-sm min-w-[500px]">
            <div className="relative">
              <input
                type="text"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
                className="w-full hover:animate-pulse pl-3 pr-10 py-3 bg-transparent placeholder:text-slate-400 text-slate-200 text-sm border border-slate-200 rounded-md transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-300 shadow-sm focus:shadow"
                placeholder="Type here your task..."
              />

              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="currentColor"
                className="absolute w-5 h-5 top-2.5 right-2.5 text-slate-600"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="size-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 6.878V6a2.25 2.25 0 0 1 2.25-2.25h7.5A2.25 2.25 0 0 1 18 6v.878m-12 0c.235-.083.487-.128.75-.128h10.5c.263 0 .515.045.75.128m-12 0A2.25 2.25 0 0 0 4.5 9v.878m13.5-3A2.25 2.25 0 0 1 19.5 9v.878m0 0a2.246 2.246 0 0 0-.75-.128H5.25c-.263 0-.515.045-.75.128m15 0A2.25 2.25 0 0 1 21 12v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6c0-.98.626-1.813 1.5-2.122"
                  />
                </svg>
              </svg>
            </div>
            <div className="flex justify-center text-center space-x-16 my-4">
              <div className="flex mt-2 justify-center text-center">
                <button className="flex justify-center text-center">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    className="size-6 flex justify-center text-center"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M10.5 6h9.75M10.5 6a1.5 1.5 0 1 1-3 0m3 0a1.5 1.5 0 1 0-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 0 1-3 0m3 0a1.5 1.5 0 0 0-3 0m-9.75 0h9.75"
                    />
                  </svg>
                  Filter
                </button>
              </div>
              <button
                onClick={openModal}
                className="bg-black dark:bg-white text-white dark:text-black px-4 py-2 rounded-full shadow-lg hover:bg-gray-800 dark:hover:bg-gray-200 transition-all duration-200"
              >
                Add Task
              </button>
            </div>
          </div>
        </header>

        {/* Modal */}
        <AnimatePresence>
          {isModalOpen && (
            <>
              {/* Fundo embaçado */}
              <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                className="fixed inset-0 bg-black bg-opacity-50 backdrop-blur-sm z-40"
                onClick={closeModal}
              />

              {/* Janela do modal */}
              <motion.div
                initial={{ opacity: 0, y: -50 }}
                animate={{ opacity: 1, y: 0 }}
                exit={{ opacity: 0, y: -50 }}
                className="fixed transform -translate-x-1/2 -translate-y-1/2 bg-white dark:bg-white p-6 rounded-lg shadow-lg z-50 w-[90%] max-w-md"
              >
                <h2 className="text-2xl font-mono font-bold text-white dark:text-slate-950 mb-4">
                  TaskCoder
                </h2>
                <form
                  onSubmit={(e) => {
                    e.preventDefault();
                    closeModal();
                  }}
                >
                  <label
                    htmlFor="title"
                    className="text-black text-sm font-mono ml-1"
                  >
                    title
                  </label>
                  <input
                    id="title"
                    type="text"
                    value={titulo}
                    onChange={(e) => setTitulo(e.target.value)}
                    className="w-full font-mono hover:animate-pulse pl-3 pr-10 py-2 bg-transparent placeholder:text-slate-400 text-slate-600 text-sm border border-slate-200 rounded-md transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-300 shadow-sm focus:shadow mb-5"
                    placeholder="Title..."
                  />

                  <label
                    htmlFor="description"
                    className="text-black text-sm font-mono ml-1"
                  >
                    description
                  </label>
                  <input
                    id="description"
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    className="w-full font-mono hover:animate-pulse pl-3 pr-10 py-2 bg-transparent placeholder:text-slate-400 text-slate-600 text-sm border border-slate-200 rounded-md transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-300 shadow-sm focus:shadow mb-5"
                    placeholder="Description..."
                  />
                  <div className="flex justify-end space-x-4">
                    <button
                      type="button"
                      onClick={closeModal}
                      className="rounded-md font-mono border border-transparent py-2 px-4 text-center text-sm transition-all text-black hover:bg-slate-100 focus:bg-slate-100 active:bg-slate-100 disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    >
                      Cancel
                    </button>
                    <button
                      type="submit"
                      onClick={handleSubmit}
                      className="px-4 py-2 text-sm font-mono text-black bg-yellow-500 rounded-md hover:bg-yellow-600 transition-all"
                    >
                      Add Task
                    </button>
                  </div>
                </form>
              </motion.div>
            </>
          )}
        </AnimatePresence>
      </div>
    </div>
  );
}
