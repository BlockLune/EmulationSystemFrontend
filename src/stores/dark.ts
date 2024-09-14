import { defineStore } from "pinia"
import { useDark, useToggle } from "@vueuse/core";

export const useDarkStore = defineStore('dark', () => {
  const isDark = useDark();
  const toggleDark = useToggle(isDark);
  return { isDark, toggleDark }
})
