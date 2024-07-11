import { ref } from "vue";
import { defineStore } from "pinia";
import axiosInstance from "../utils/axiosInstance";
import type { Exercise, NewExercise } from "../types";

const useExercisesStore = defineStore('exercises', () => {
  const exercises = ref<Exercise[] | undefined>(undefined);
  const selectExercisesByPage = async (
    exerciseName: string,
    pageNum: string,
    pageSize: string,
    status: string
  ) => {
    try {
      const response = await axiosInstance.post("/exercise/selectByPage", {
        exerciseName: exerciseName,
        pageNum: pageNum,
        pageSize: pageSize,
        status: status,
      });
      // console.log("POST /exercise/selectByPage: ", response);
      exercises.value = response.data.data.list;
      // console.log(exercises.value);
      return response;
    } catch (err) {
      console.error(err);
    }
  }
  const createExercise = async (newExercise: NewExercise) => {
    try {
      const response = await axiosInstance.post("/exercise/createExercise", newExercise)
      return response;
    } catch (err) {
      console.error(err);
    }
  }
  return { exercises, selectExercisesByPage, createExercise };
});

export default useExercisesStore;
